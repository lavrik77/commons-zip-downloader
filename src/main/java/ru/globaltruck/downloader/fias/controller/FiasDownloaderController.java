package ru.globaltruck.downloader.fias.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FiasDownloaderController {

    @Operation(summary = "Загрузка объектов адресации из ГАР")
    @GetMapping(value = "/download/addressobject")
    void downloadAO(@RequestParam(name = "path") String path,
                    @RequestParam(name = "fileInArc", required = false) String fileInAscName);

    @Operation(summary = "Загрузка домов из ГАР")
    @GetMapping(value = "/download/house")
    void downloadHouses(@RequestParam(name = "path") String path,
                    @RequestParam(name = "fileInArc", required = false) String fileInAscName);
}
