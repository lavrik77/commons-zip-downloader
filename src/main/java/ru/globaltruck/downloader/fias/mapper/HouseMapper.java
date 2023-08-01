package ru.globaltruck.downloader.fias.mapper;

import ru.globaltruck.downloader.fias.repository.entity.HouseEntity;
import ru.globaltruck.downloader.fias.util.GarObjectMap;

public class HouseMapper implements Mapper<HouseEntity> {

    @Override
    public HouseEntity map(GarObjectMap dto) {
        HouseEntity entity = new HouseEntity();

        entity.setId(dto.get("ID"));
        entity.setObjectId(dto.get("OBJECTID"));
        entity.setObjectGuid(dto.get("OBJECTGUID"));
        entity.setChangeId(dto.get("CHANGEID"));
        entity.setHouseNum(dto.get("HOUSENUM"));
        entity.setAddNum1(dto.get("ADDNUM1"));
        entity.setAddNum2(dto.get("ADDNUM2"));
        entity.setHouseType(dto.get("HOUSETYPE"));
        entity.setAddType1(dto.get("ADDTYPE1"));
        entity.setAddType2(dto.get("ADDTYPE2"));
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
