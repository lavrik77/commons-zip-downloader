package ru.globaltruck.downloader.fias.repository;

import org.springframework.data.repository.CrudRepository;
import ru.globaltruck.downloader.fias.repository.entity.AddressObjectEntity;

public interface AddressObjectRepository extends CrudRepository<AddressObjectEntity, Long> {
}
