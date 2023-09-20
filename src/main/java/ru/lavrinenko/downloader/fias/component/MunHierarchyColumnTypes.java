package ru.lavrinenko.downloader.fias.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Slf4j
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

    @Override
    public void handleException(Throwable t) {
        log.error(t.getMessage(), t);
    }
}
