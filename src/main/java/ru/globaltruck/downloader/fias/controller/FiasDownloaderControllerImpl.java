package ru.globaltruck.downloader.fias.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import ru.globaltruck.downloader.fias.service.ArcFileDownloadService;
import ru.globaltruck.downloader.fias.service.FilesDownloadService;
import ru.globaltruck.downloader.fias.util.LoadingRepeaterUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FiasDownloaderControllerImpl implements FiasDownloaderController {

    private final ArcFileDownloadService arcFileDownloadService;
    private final FilesDownloadService filesDownloadService;

    @Override
    public ResponseEntity<String> downloadAO(String path, String fileInAscName) {
        if (fileInAscName != null) {
            LoadingRepeaterUtil.setFileName(fileInAscName);
            LoadingRepeaterUtil.onErrFlag();
        } else {
            LoadingRepeaterUtil.clearName();
            LoadingRepeaterUtil.offErrFlag();
        }
        arcFileDownloadService.downloadAddressObjects(path);
        return ResponseEntity.ok("Загрузка начата");
    }

    @Override
    public ResponseEntity<String> downloadHouses(String path, String fileInAscName) {
        if (fileInAscName != null) {
            LoadingRepeaterUtil.setFileName(fileInAscName);
            LoadingRepeaterUtil.onErrFlag();
        } else {
            LoadingRepeaterUtil.clearName();
            LoadingRepeaterUtil.offErrFlag();
        }
        arcFileDownloadService.downloadHouses(path);
        return ResponseEntity.ok("Загрузка начата");
    }

    @Override
    public ResponseEntity<String> downloadAdmHierarchy(String path, String fileInAscName) {
        if (fileInAscName != null) {
            LoadingRepeaterUtil.setFileName(fileInAscName);
            LoadingRepeaterUtil.onErrFlag();
        } else {
            LoadingRepeaterUtil.clearName();
            LoadingRepeaterUtil.offErrFlag();
        }
        arcFileDownloadService.downloadAdmHierarchy(path);
        return ResponseEntity.ok("Загрузка начата");
    }

    @Override
    public ResponseEntity<String> downloadMunHierarchy(String path, String fileInAscName) {
        if (fileInAscName != null) {
            LoadingRepeaterUtil.setFileName(fileInAscName);
            LoadingRepeaterUtil.onErrFlag();
        } else {
            LoadingRepeaterUtil.clearName();
            LoadingRepeaterUtil.offErrFlag();
        }
        arcFileDownloadService.downloadMunHierarchy(path);
        return ResponseEntity.ok("Загрузка начата");
    }

    public ResponseEntity<String> downloadAll(String path, String fileInAscName) {
        try {
            filesDownloadService.downloadAll(path);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            return ResponseEntity.status(500).body(ResponseEntity.of(Optional.of(e.getMessage())).toString());
        }
        return ResponseEntity.ok("Загрузка начата");
    }

}
