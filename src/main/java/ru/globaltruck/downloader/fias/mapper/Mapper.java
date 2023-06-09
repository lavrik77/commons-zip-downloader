package ru.globaltruck.downloader.fias.mapper;

import ru.globaltruck.downloader.fias.repository.entity.BaseEntity;
import ru.globaltruck.downloader.fias.util.GarObjectMap;

public interface Mapper<E> {

    public E map(GarObjectMap dto);
}
