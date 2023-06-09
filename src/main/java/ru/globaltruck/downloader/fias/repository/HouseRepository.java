package ru.globaltruck.downloader.fias.repository;

import org.springframework.data.repository.CrudRepository;
import ru.globaltruck.downloader.fias.repository.entity.HouseEntity;

public interface HouseRepository extends CrudRepository<HouseEntity, Long> {
}
