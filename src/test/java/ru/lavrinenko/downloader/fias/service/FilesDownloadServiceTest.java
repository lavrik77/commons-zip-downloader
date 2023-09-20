package ru.lavrinenko.downloader.fias.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.xml.sax.SAXException;
import ru.lavrinenko.downloader.fias.error.UnZipError;
import ru.lavrinenko.downloader.fias.repository.AddressObjectRepository;
import ru.lavrinenko.downloader.fias.repository.AdmHierarchyRepository;
import ru.lavrinenko.downloader.fias.repository.HouseRepository;
import ru.lavrinenko.downloader.fias.repository.MunHierarchyRepository;
import ru.lavrinenko.downloader.fias.repository.entity.AddressObjectEntity;
import ru.lavrinenko.downloader.fias.util.HandlerFactory;
import ru.lavrinenko.downloader.fias.util.IntegrationAppTestWithDB;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationAppTestWithDB
class FilesDownloadServiceTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AddressObjectRepository addressObjectRepository;
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    AdmHierarchyRepository admHierarchyRepository;
    @Autowired
    MunHierarchyRepository munHierarchyRepository;

    FilesDownloadService filesDownloadService;
//    String resource;

    @BeforeEach
    void beforeTest() {
        filesDownloadService = new FilesDownloadService(new HandlerFactory(
                houseRepository,
                addressObjectRepository,
                admHierarchyRepository,
                munHierarchyRepository));
    }

    @Test
    void downloadAll() throws IOException, ParserConfigurationException, SAXException {
        String resource = new DefaultResourceLoader().getResource("/gar").getFile().getPath();
        FilesDownloadService filesDownloadServiceMock = Mockito.mock(FilesDownloadService.class);

        filesDownloadServiceMock.downloadAll(resource);

        Mockito.verify(filesDownloadServiceMock).downloadAll(resource);
    }

//    @Test
    void loadRegion() {
    }

    @Test
    void parseFile() throws UnZipError, IOException {
        Path file = Path.of(
                new DefaultResourceLoader()
                        .getResource("gar/02/AS_ADDR_OBJ_20230529_d57bfe3c-0f04-495c-b9b2-88502299bf74.XML")
                        .getURI());
        HandlerFactory handlerFactory = new HandlerFactory(
                houseRepository,
                addressObjectRepository,
                admHierarchyRepository,
                munHierarchyRepository);
        filesDownloadService.parseFile(file, handlerFactory.getAddressObjectHandler());

        List<AddressObjectEntity> entities = (List<AddressObjectEntity>) addressObjectRepository.findAll();

        assertEquals(7, entities.size());
    }

    @Test
    void listFiles() throws IOException {
        Path resource = Path.of(new DefaultResourceLoader().getResource("/gar/02").getURI());
        List<Path> files = filesDownloadService.listFiles(resource);

        assertEquals(4, files.size());
    }

    @Test
    void listDirectories() throws IOException {
        String resource = new DefaultResourceLoader().getResource("/gar").getFile().getPath();
        List<Path> dirs = filesDownloadService.listDirectories(resource);

        assertEquals(1, dirs.size());
    }
}