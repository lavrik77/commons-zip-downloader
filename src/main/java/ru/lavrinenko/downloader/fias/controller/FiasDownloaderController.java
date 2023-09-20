package ru.lavrinenko.downloader.fias.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FiasDownloaderController {

    @Operation(summary = "Загрузка объектов адресации из ГАР")
    @GetMapping(value = "/download/addressobject")
    ResponseEntity<String> downloadAO(@RequestParam(name = "path") String path,
                    @RequestParam(name = "fileInArc", required = false) String fileInAscName);

    @Operation(summary = "Загрузка домов из ГАР")
    @GetMapping(value = "/download/house")
    ResponseEntity<String> downloadHouses(@RequestParam(name = "path") String path,
                    @RequestParam(name = "fileInArc", required = false) String fileInAscName);

    @Operation(summary = "Загрузка административной иерархии объектов адресации из ГАР")
    @GetMapping(value = "/download/admhierarchy")
    ResponseEntity<String> downloadAdmHierarchy(@RequestParam(name = "path") String path,
                                                @RequestParam(name = "fileInArc", required = false) String fileInAscName);


    @Operation(summary = "Загрузка муниципальной иерархии объектов адресации из ГАР")
    @GetMapping(value = "/download/munhierarchy")
    ResponseEntity<String> downloadMunHierarchy(@RequestParam(name = "path") String path,
                                                @RequestParam(name = "fileInArc", required = false) String fileInAscName);

    @Operation(summary = "Загрузка всех файлов из ГАР")
    @GetMapping(value = "/download/all")
    ResponseEntity<String> downloadAll(String path, String fileInAscName);
}
