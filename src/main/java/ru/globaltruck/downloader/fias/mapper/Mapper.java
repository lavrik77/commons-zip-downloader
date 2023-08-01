package ru.globaltruck.downloader.fias.mapper;

import ru.globaltruck.downloader.fias.util.GarObjectMap;

public interface Mapper<E> {

    E map(GarObjectMap dto);
}
