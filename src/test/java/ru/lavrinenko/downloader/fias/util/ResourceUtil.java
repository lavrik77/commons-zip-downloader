package ru.lavrinenko.downloader.fias.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class ResourceUtil {

    public static String readFileToString(String path) throws IOException {
        return FileCopyUtils.copyToString(getReader(path));
    }

    public static Reader getReader(String path) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        return new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
