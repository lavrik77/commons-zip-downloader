package ru.lavrinenko.downloader.fias.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ru.lavrinenko.downloader.fias.handler.AbstractHandler;
import ru.lavrinenko.downloader.fias.error.UnZipError;
import ru.lavrinenko.downloader.fias.util.HandlerFactory;
import ru.lavrinenko.downloader.fias.util.LoadingRepeaterUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static ru.lavrinenko.downloader.fias.util.FileNamePattern.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ArcFileDownloadService {
    private final HandlerFactory factory;

    @Async
    public void downloadAddressObjects(String filePath) {
        try {
            log.debug("AddressObjects downloading started");
            download(filePath, factory.getAddressObjectHandler(), ADDR_OBJ_PATTERN);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    @Async
    public void downloadHouses(String filePath) {
        try {
            log.debug("Houses downloading started");
            download(filePath, factory.getHouseHandler(), HOUSE_PATTERN);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    @Async
    public void downloadAdmHierarchy(String filePath) {
        try {
            log.debug("Hierarchy downloading started");
            download(filePath, factory.getAdmHierarchyHandler(), ADM_HIERARCHY_PATTERN);
        } catch (UnZipError e) {
            LoadingRepeaterUtil.clearName();
            log.error(e.getMessage(), e);
        }
    }

    @Async
    public void downloadMunHierarchy(String filePath) {
        try {
            log.debug("Hierarchy downloading started");
            download(filePath, factory.getMunHierarchyHandler(), MUN_HIERARCHY_PATTERN);
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
            HttpsURLConnection connection = null;
            if (filePath.toLowerCase().startsWith("http")) {
                connection = (HttpsURLConnection) new URL(filePath).openConnection();
            }
            WontCloseZIS zis;
            SAXParserFactory factory = SAXParserFactory.newInstance();
            if (filePath.toLowerCase().startsWith("http")) {
                zis = getZipInputStream(connection);
            } else {
                zis = new WontCloseZIS(new FileInputStream(filePath));
            }
            log.debug("Start loading file: {}", filePath);
            ZipEntry zipEntry = zis == null ? null : zis.getNextEntry();
            while (zipEntry != null && !LoadingRepeaterUtil.equals(zipEntry.getName())) {
                zipEntry = zis.getNextEntry();
            }
            LoadingRepeaterUtil.offErrFlag();
            if (zis != null) {
                while (zipEntry != null) {
                    if (pattern.matcher(zipEntry.getName()).find() && zipEntry.getSize() > 60) {
                        LoadingRepeaterUtil.setFileName(zipEntry.getName());
                        log.info("Entry name: {}", zipEntry.getName());
                        factory.newSAXParser().parse(zis, handler);
                    }
                    zipEntry = zis.getNextEntry();
                }
            }
            LoadingRepeaterUtil.clearName();
            LoadingRepeaterUtil.offErrFlag();
            if (zis != null) {
                zis.reallyClose();
            }
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

    private static WontCloseZIS getZipInputStream(HttpsURLConnection connection) throws IOException {
        if (connection == null) {
            return null;
        }
        connection.addRequestProperty("Cache-Control", "no-cache");
        connection.addRequestProperty("Connection", "keep-alive");
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setConnectTimeout(600000);
        connection.setReadTimeout(7200000);

        log.debug(connection.getRequestProperties());
        return new WontCloseZIS(connection.getInputStream());
    }

    static class WontCloseZIS extends ZipInputStream {
        public WontCloseZIS(InputStream in) {
            super(in);
        }

        @Override
        public void close() {
        }

        public void reallyClose() throws IOException {
            super.close();
        }
    }
}
