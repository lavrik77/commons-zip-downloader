package ru.lavrinenko.downloader.fias.mapper;

import ru.lavrinenko.downloader.fias.util.GarObjectMap;

public interface Mapper<E> {

    E map(GarObjectMap dto);
}
