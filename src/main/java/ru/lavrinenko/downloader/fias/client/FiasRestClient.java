package ru.lavrinenko.downloader.fias.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lavrinenko.downloader.fias.client.model.GarFileInfo;

@FeignClient(
        name = "fias-service-rest",
        url = "${config.client.fias.url}",
        decode404 = true
)
public interface FiasRestClient {

    @GetMapping(value = "${config.client.fias.path}")
    GarFileInfo send();
}
