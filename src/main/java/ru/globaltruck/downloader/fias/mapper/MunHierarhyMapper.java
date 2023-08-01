package ru.globaltruck.downloader.fias.mapper;

import ru.globaltruck.downloader.fias.repository.entity.MunHierarchyEntity;
import ru.globaltruck.downloader.fias.util.GarObjectMap;

public class MunHierarhyMapper implements Mapper<MunHierarchyEntity> {

    @Override
    public MunHierarchyEntity map(GarObjectMap dto) {
        MunHierarchyEntity entity = new MunHierarchyEntity();

        entity.setId(dto.get("ID"));
        entity.setObjectId(dto.get("OBJECTID"));
        entity.setParentObjId(dto.get("PARENTOBJID"));
        entity.setChangeId(dto.get("CHANGEID"));
        entity.setOktmo(dto.get("OKTMO"));
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
