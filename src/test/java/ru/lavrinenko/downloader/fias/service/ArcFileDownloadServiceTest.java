package ru.lavrinenko.downloader.fias.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import ru.lavrinenko.downloader.fias.handler.AbstractHandler;
import ru.lavrinenko.downloader.fias.repository.AddressObjectRepository;
import ru.lavrinenko.downloader.fias.repository.AdmHierarchyRepository;
import ru.lavrinenko.downloader.fias.repository.HouseRepository;
import ru.lavrinenko.downloader.fias.repository.MunHierarchyRepository;
import ru.lavrinenko.downloader.fias.repository.entity.AddressObjectEntity;
import ru.lavrinenko.downloader.fias.repository.entity.AdmHierarchyEntity;
import ru.lavrinenko.downloader.fias.repository.entity.HouseEntity;
import ru.lavrinenko.downloader.fias.repository.entity.MunHierarchyEntity;
import ru.lavrinenko.downloader.fias.util.HandlerFactory;
import ru.lavrinenko.downloader.fias.util.IntegrationAppTestWithDB;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.lavrinenko.downloader.fias.util.FileNamePattern.HOUSE_PATTERN;

@IntegrationAppTestWithDB
class ArcFileDownloadServiceTest {

    @Autowired
    AddressObjectRepository addressObjectRepository;
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    AdmHierarchyRepository admHierarchyRepository;
    @Autowired
    MunHierarchyRepository munHierarchyRepository;

    ArcFileDownloadService arcFileDownloadService;
    String resource;
    HandlerFactory handlerFactory;

    @BeforeEach
    void beforeTest() throws IOException {
        resource = new DefaultResourceLoader().getResource("/gar/gar-test.zip").getFile().getPath();
        handlerFactory = new HandlerFactory(
                houseRepository,
                addressObjectRepository,
                admHierarchyRepository,
                munHierarchyRepository);
        arcFileDownloadService = new ArcFileDownloadService(handlerFactory);
    }


    @Test
    void downloadAddressObjects() {
        arcFileDownloadService.downloadAddressObjects(resource);
        List<AddressObjectEntity> entities = (List<AddressObjectEntity>) addressObjectRepository.findAll();

        assertEquals(7, entities.size());
    }

    @Test
    void downloadHouses() {
        arcFileDownloadService.downloadHouses(resource);
        List<HouseEntity> entities = (List<HouseEntity>) houseRepository.findAll();

        assertEquals(21, entities.size());
    }

    @Test
    void downloadAdmHierarchy() {
        arcFileDownloadService.downloadAdmHierarchy(resource);
        List<AdmHierarchyEntity> entities = (List<AdmHierarchyEntity>) admHierarchyRepository.findAll();

        assertEquals(13, entities.size());
    }

    @Test
    void downloadMunHierarchy() {
        arcFileDownloadService.downloadMunHierarchy(resource);
        List<MunHierarchyEntity> entities = (List<MunHierarchyEntity>) munHierarchyRepository.findAll();

        assertEquals(8, entities.size());
    }

    @Test
    void download() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method downloadMethod = ArcFileDownloadService.class.getDeclaredMethod(
                "download",
                String.class,
                AbstractHandler.class,
                Pattern.class);
        downloadMethod.setAccessible(true);

        downloadMethod.invoke(arcFileDownloadService, resource, handlerFactory.getHouseHandler(), HOUSE_PATTERN);
        List<HouseEntity> entities = (List<HouseEntity>) houseRepository.findAll();
        assertEquals(21, entities.size());
    }
}