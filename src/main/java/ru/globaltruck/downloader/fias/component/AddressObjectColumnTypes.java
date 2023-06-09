package ru.globaltruck.downloader.fias.component;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class AddressObjectColumnTypes implements ColumnTypes {

    public AddressObjectColumnTypes() {
        types.put("ID", Long.class);
        types.put("OBJECTID", Long.class);
        types.put("OBJECTGUID", UUID.class);
        types.put("CHANGEID", Long.class);
        types.put("NAME", String.class);
        types.put("TYPENAME", String.class);
        types.put("LEVEL", Integer.class);
        types.put("OPERTYPEID", Integer.class);
        types.put("PREVID", Long.class);
        types.put("NEXTID", Long.class);
        types.put("UPDATEDATE", OffsetDateTime.class);
        types.put("STARTDATE", OffsetDateTime.class);
        types.put("ENDDATE", OffsetDateTime.class);
        types.put("ISACTUAL", Boolean.class);
        types.put("ISACTIVE", Boolean.class);
    }
}
