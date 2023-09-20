package ru.lavrinenko.downloader.fias.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ru.lavrinenko.downloader.fias.handler.AbstractHandler;
import ru.lavrinenko.downloader.fias.error.UnZipError;
import ru.lavrinenko.downloader.fias.util.HandlerFactory;
import ru.lavrinenko.downloader.fias.util.LoadingRepeaterUtil;
import ru.lavrinenko.downloader.fias.util.XmlFileParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ru.lavrinenko.downloader.fias.util.FileNamePattern.ADDR_OBJ_PATTERN;
import static ru.lavrinenko.downloader.fias.util.FileNamePattern.ADM_HIERARCHY_PATTERN;
import static ru.lavrinenko.downloader.fias.util.FileNamePattern.HOUSE_PATTERN;
import static ru.lavrinenko.downloader.fias.util.FileNamePattern.MUN_HIERARCHY_PATTERN;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilesDownloadService {

    private final HandlerFactory handlerFactory;

    @Async(value = "taskExecutor")
    public void downloadAll(String dir) throws IOException, ParserConfigurationException, SAXException {
        List<Path> directories = listDirectories(dir);
        for (Path region : directories) {
            loadRegion(region);
        }
    }

    @Async(value = "fileHandlerTaskExecutor")
    void loadRegion(Path region) throws IOException {
        List<Path> files = listFiles(region);
        XmlFileParser parser = new XmlFileParser();
        files.forEach(file -> {
            try {
                if (ADDR_OBJ_PATTERN.matcher(file.getFileName().toString()).find()) {
                    parser.parse(file, handlerFactory.getAddressObjectHandler());
                }
                if (ADM_HIERARCHY_PATTERN.matcher(file.getFileName().toString()).find()) {
                    parser.parse(file, handlerFactory.getAdmHierarchyHandler());
                }
                if (HOUSE_PATTERN.matcher(file.getFileName().toString()).find()) {
                    parser.parse(file, handlerFactory.getHouseHandler());
                }
                if (MUN_HIERARCHY_PATTERN.matcher(file.getFileName().toString()).find()) {
                    parser.parse(file, handlerFactory.getMunHierarchyHandler());
                }
            } catch (UnZipError e) {
                log.error(e.getMessage(), e);
            }

        });
    }

    @Async(value = "fileHandlerTaskExecutor")
    void parseFile(Path p, AbstractHandler handler) throws UnZipError {
        try {
            log.info("File parsing: {}", p.toString());
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(p.toFile()));
            saxParser.parse(bis, handler);
            log.info("Done: {}", p.toString());
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new UnZipError(
                    String.format("Произошла ошибка обработки файла '%s'", LoadingRepeaterUtil.getFileName()),
                    e);
        }
    }

    public List<Path> listFiles(Path dir) throws IOException {
        List<Path> fileList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if (!Files.isDirectory(path) && !Files.isHidden(path)) {
                    fileList.add(path);
                }
            }
        }
        return fileList.stream().sorted().toList();
    }

    public List<Path> listDirectories(String dir) throws IOException {
        List<Path> dirList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (Files.isDirectory(path) && !Files.isHidden(path)) {
                    dirList.add(path);
                }
            }
        }
        return dirList.stream().sorted().toList();
    }
}
