package ru.globaltruck.downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@EnableFeignClients
@SpringBootApplication
public class CommonsZipDownloaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsZipDownloaderApplication.class, args);
    }

}
