package ru.lavrinenko.downloader.fias.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.CrudRepository;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ru.lavrinenko.downloader.fias.component.ColumnTypes;
import ru.lavrinenko.downloader.fias.mapper.Mapper;
import ru.lavrinenko.downloader.fias.util.GarObjectMap;

import java.util.Optional;

@Log4j2
public abstract class AbstractHandler<E, R extends CrudRepository<E, Long>> extends DefaultHandler {
    protected int nodeCnt = 0;

    protected void process(Attributes attr, ColumnTypes columnTypes, Mapper<E> mapper, R repository) {
        GarObjectMap data = new GarObjectMap();
        int attrCnt = attr.getLength();
        for (int i = 0; i < attrCnt; i++) {
            String name = attr.getQName(i);
            data.put(name, columnTypes.getCastValue(name, attr.getValue(i).replaceAll("'", "''")));
        }
        log.debug("Record ID = {}", Optional.ofNullable(data.get("ID")).toString());
        log.trace("\rnames: " + data.keySet());
        log.trace("\rvalues: " + data.values());

        repository.save(mapper.map(data));

    }
}
