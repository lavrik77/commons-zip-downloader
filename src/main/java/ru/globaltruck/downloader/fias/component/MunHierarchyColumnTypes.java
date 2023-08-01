package ru.globaltruck.downloader.fias.component;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class MunHierarchyColumnTypes implements ColumnTypes {

    public MunHierarchyColumnTypes() {
        types.put("ID", Long.class);
        types.put("OBJECTID", Long.class);
        types.put("CHANGEID", Long.class);

        types.put("PARENTOBJID", Long.class);
        types.put("OKTMO", String.class);
        types.put("PATH", String.class);

        types.put("OPERTYPEID", Integer.class);
        types.put("PREVID", Long.class);
        types.put("NEXTID", Long.class);
        types.put("UPDATEDATE", OffsetDateTime.class);
        types.put("STARTDATE", OffsetDateTime.class);
        types.put("ENDDATE", OffsetDateTime.class);
        types.put("ISACTIVE", Boolean.class);
    }
}
