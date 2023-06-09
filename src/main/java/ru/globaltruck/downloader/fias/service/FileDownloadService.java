package ru.globaltruck.downloader.fias.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ru.globaltruck.downloader.fias.handler.AbstractHandler;
import ru.globaltruck.downloader.fias.handler.AddressObjectHandler;
import ru.globaltruck.downloader.fias.handler.HouseHandler;
import ru.globaltruck.downloader.fias.error.UnZipError;
import ru.globaltruck.downloader.fias.util.LoadingRepeaterUtil;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static ru.globaltruck.downloader.fias.util.FileNamePattern.ADDR_OBJ;
import static ru.globaltruck.downloader.fias.util.FileNamePattern.HOUSES;

@Log4j2
@Service
@RequiredArgsConstructor
public class FileDownloadService {

    private final AddressObjectHandler addressObjectHandler;

    private final HouseHandler houseHandler;

    public void downloadAddressObjects(String filePath) {
        try {
            download(filePath, addressObjectHandler, ADDR_OBJ);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    public void downloadHouses(String filePath) {
        try {
            download(filePath, houseHandler, HOUSES);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    @SuppressWarnings("rawtypes")
    private void download(String filePath,
                          AbstractHandler handler,
                          Pattern pattern) throws UnZipError {
        try {
            ZipInputStream zis;
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            if (filePath.toLowerCase().startsWith("http")) {
                URLConnection connection = new URL(filePath).openConnection();
                connection.setUseCaches(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(18000000);
                connection.setReadTimeout(18000000);
                zis = new ZipInputStream(connection.getInputStream());
            } else {
                zis = new ZipInputStream(new FileInputStream(filePath));
            }
            log.info("Start loading file: {}", filePath);
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null && !LoadingRepeaterUtil.equals(zipEntry.getName())) {
                zipEntry = zis.getNextEntry();
            }
            LoadingRepeaterUtil.offErrFlag();
            while (zipEntry != null) {
                if (pattern.matcher(zipEntry.getName()).find() && zipEntry.getSize() > 60) {
                    LoadingRepeaterUtil.setFileName(zipEntry.getName());
                    System.out.println("\n");
                    log.info("Entry name: {}", zipEntry.getName());
                    Scanner sc = new Scanner(zis);
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        log.debug(line);
                        handler.setNodeCnt(0);
                        handler.setRecordCnt(0);
                        saxParser.parse(new ByteArrayInputStream(line.getBytes()), handler);
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            LoadingRepeaterUtil.clearName();
            zis.close();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            LoadingRepeaterUtil.onErrFlag();
            throw new UnZipError(String
                    .format(
                            "Произошла ошибка обработки архива '%s' на файле '%s'",
                            filePath,
                            LoadingRepeaterUtil.getFileName()),
                    e);
        }

        log.info("End loading file");
    }
}
