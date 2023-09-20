package ru.lavrinenko.downloader.fias.util;

import java.util.HashMap;

public class GarObjectMap extends HashMap<String, Object> {

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) super.get(key);
    }
}
