package ru.globaltruck.downloader.fias.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.globaltruck.downloader.fias.service.FileDownloadService;
import ru.globaltruck.downloader.fias.util.LoadingRepeaterUtil;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FiasDownloaderControllerImpl implements FiasDownloaderController {

    private final FileDownloadService fileDownloadService;

    @Override
    public void downloadAO(String path, String fileInAscName) {
        if (fileInAscName != null) {
            LoadingRepeaterUtil.setFileName(fileInAscName);
            LoadingRepeaterUtil.onErrFlag();
        }
        fileDownloadService.downloadAddressObjects(path);
    }

    @Override
    public void downloadHouses(String path, String fileInAscName) {
        if (fileInAscName != null) {
            LoadingRepeaterUtil.setFileName(fileInAscName);
            LoadingRepeaterUtil.onErrFlag();
        }
        fileDownloadService.downloadHouses(path);
    }
}
