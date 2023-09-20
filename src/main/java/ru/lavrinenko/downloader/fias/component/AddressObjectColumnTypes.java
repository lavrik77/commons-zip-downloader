package ru.lavrinenko.downloader.fias.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
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

    @Override
    public void handleException(Throwable t) {
        log.error(t.getMessage(), t);
    }
}
