package ru.lavrinenko.downloader.fias.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.xml.sax.SAXException;
import ru.lavrinenko.downloader.fias.handler.AbstractHandler;
import ru.lavrinenko.downloader.fias.error.UnZipError;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class XmlFileParser {

    @Async(value = "fileHandlerTaskExecutor")
    public void parse(Path p, AbstractHandler handler) throws UnZipError {
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

}
