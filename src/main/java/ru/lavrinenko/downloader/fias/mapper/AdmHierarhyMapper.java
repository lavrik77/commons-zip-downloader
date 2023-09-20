package ru.lavrinenko.downloader.fias.mapper;

import ru.lavrinenko.downloader.fias.repository.entity.AdmHierarchyEntity;
import ru.lavrinenko.downloader.fias.util.GarObjectMap;

public class AdmHierarhyMapper implements Mapper<AdmHierarchyEntity> {

    @Override
    public AdmHierarchyEntity map(GarObjectMap dto) {
        AdmHierarchyEntity entity = new AdmHierarchyEntity();

        entity.setId(dto.get("ID"));
        entity.setObjectId(dto.get("OBJECTID"));
        entity.setParentObjId(dto.get("PARENTOBJID"));
        entity.setChangeId(dto.get("CHANGEID"));
        entity.setRegionCode(dto.get("REGIONCODE"));
        entity.setAreaCode(dto.get("AREACODE"));
        entity.setCityCode(dto.get("CITYCODE"));
        entity.setPlaceCode(dto.get("PLACECODE"));
        entity.setPlanCode(dto.get("PLANCODE"));
        entity.setStreetCode(dto.get("STREETCODE"));
        entity.setPrevId(dto.get("PREVID"));
        entity.setNextId(dto.get("NEXTID"));
        entity.setUpdateDate(dto.get("UPDATEDATE"));
        entity.setStartDate(dto.get("STARTDATE"));
        entity.setEndDate(dto.get("ENDDATE"));
        entity.setIsActive(dto.get("ISACTIVE"));
        entity.setPath(dto.get("PATH"));

        return entity;
    }
}
