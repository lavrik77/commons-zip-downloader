package ru.lavrinenko.downloader.fias.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lavrinenko.downloader.fias.repository.entity.HouseEntity;

public interface HouseRepository extends CrudRepository<HouseEntity, Long> {
}
