package ru.lavrinenko.downloader.fias.mapper;

import ru.lavrinenko.downloader.fias.repository.entity.AddressObjectEntity;
import ru.lavrinenko.downloader.fias.util.GarObjectMap;

public class AddressObjectMapper implements Mapper<AddressObjectEntity> {

    @Override
    public AddressObjectEntity map(GarObjectMap dto) {
        AddressObjectEntity entity = new AddressObjectEntity();

        entity.setId(dto.get("ID"));
        entity.setObjectId(dto.get("OBJECTID"));
        entity.setObjectGuid(dto.get("OBJECTGUID"));
        entity.setChangeId(dto.get("CHANGEID"));
        entity.setName(dto.get("NAME"));
        entity.setTypeName(dto.get("TYPENAME"));
        entity.setLevel(dto.get("LEVEL"));
        entity.setOperTypeId(dto.get("OPERTYPEID"));
        entity.setPrevId(dto.get("PREVID"));
        entity.setNextId(dto.get("NEXTID"));
        entity.setUpdateDate(dto.get("UPDATEDATE"));
        entity.setStartDate(dto.get("STARTDATE"));
        entity.setEndDate(dto.get("ENDDATE"));
        entity.setIsActual(dto.get("ISACTUAL"));
        entity.setIsActive(dto.get("ISACTIVE"));

        return entity;
    }
}
