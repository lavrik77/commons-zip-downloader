package ru.globaltruck.downloader.fias.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ru.globaltruck.downloader.fias.handler.AbstractHandler;
import ru.globaltruck.downloader.fias.error.UnZipError;
import ru.globaltruck.downloader.fias.util.HandlerFactory;
import ru.globaltruck.downloader.fias.util.LoadingRepeaterUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static ru.globaltruck.downloader.fias.util.FileNamePattern.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ArcFileDownloadService {
    private final HandlerFactory factory;

    @Async
    public void downloadAddressObjects(String filePath) {
        try {
            log.info("AddressObjects downloading started");
            download(filePath, factory.getAddressObjectHandler(), ADDR_OBJ_PATTERN);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    @Async
    public void downloadHouses(String filePath) {
        try {
            log.info("Houses downloading started");
            download(filePath, factory.getHouseHandler(), HOUSE_PATTERN);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    @Async
    public void downloadAdmHierarchy(String filePath) {
        try {
            log.info("Hierarchy downloading started");
            download(filePath, factory.getAdmHierarchyHandler(), ADM_HIERARCHY_PATTERN);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    @Async
    public void downloadMunHierarchy(String filePath) {
        try {
            log.info("Hierarchy downloading started");
            download(filePath, factory.getAdmHierarchyHandler(), ADM_HIERARCHY_PATTERN);
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
            HttpsURLConnection connection = null;
            if (filePath.toLowerCase().startsWith("http")) {
                connection = (HttpsURLConnection) new URL(filePath).openConnection();
                connection.addRequestProperty("Cache-Control", "no-cache");
                connection.addRequestProperty("Connection", "keep-alive");
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setConnectTimeout(600000);
                connection.setReadTimeout(7200000);
                log.info(connection.getRequestProperties());
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
            BufferedInputStream bis;
            while (zipEntry != null) {
                if (pattern.matcher(zipEntry.getName()).find() && zipEntry.getSize() > 60) {
                    LoadingRepeaterUtil.setFileName(zipEntry.getName());

                    log.info("Entry name: {}", zipEntry.getName());
//                    Scanner sc = new Scanner(zis);
//                    while (sc.hasNextLine()) {
//                        String line = sc.nextLine();
//                        log.info(line);
                    bis = new BufferedInputStream(zis, 512);
                    saxParser.parse(bis /*new ByteArrayInputStream(line.getBytes())*/, handler);
//                    }
                    bis.close();
                }
                zipEntry = zis.getNextEntry();
            }
            LoadingRepeaterUtil.clearName();
            LoadingRepeaterUtil.offErrFlag();
            zis.close();
            if (connection != null) {
                connection.disconnect();
            }
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
