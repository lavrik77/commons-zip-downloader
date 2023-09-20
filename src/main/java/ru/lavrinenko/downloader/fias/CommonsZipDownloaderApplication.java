package ru.lavrinenko.downloader.fias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CommonsZipDownloaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsZipDownloaderApplication.class, args);
    }

}
