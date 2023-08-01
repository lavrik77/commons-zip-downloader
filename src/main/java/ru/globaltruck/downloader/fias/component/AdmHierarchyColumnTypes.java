package ru.globaltruck.downloader.fias.component;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class AdmHierarchyColumnTypes implements ColumnTypes {

    public AdmHierarchyColumnTypes() {
        types.put("ID", Long.class);
        types.put("OBJECTID", Long.class);
        types.put("CHANGEID", Long.class);

        types.put("PARENTOBJID", Long.class);
        types.put("REGIONCODE", String.class);
        types.put("AREACODE", String.class);
        types.put("CITYCODE", String.class);
        types.put("PLACECODE", String.class);
        types.put("PLANCODE", String.class);
        types.put("STREETCODE", String.class);
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
