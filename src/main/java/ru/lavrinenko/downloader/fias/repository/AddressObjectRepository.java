package ru.lavrinenko.downloader.fias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lavrinenko.downloader.fias.repository.entity.AddressObjectEntity;

@Repository
public interface AddressObjectRepository extends CrudRepository<AddressObjectEntity, Long> {
}
