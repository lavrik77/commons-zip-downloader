package ru.globaltruck.downloader.fias.handler;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ru.globaltruck.downloader.fias.component.ColumnTypes;
import ru.globaltruck.downloader.fias.mapper.Mapper;
import ru.globaltruck.downloader.fias.util.GarObjectMap;

import java.util.Optional;

@Log4j2
@Setter
@Component
@RequiredArgsConstructor
public abstract class AbstractHandler<E, R extends CrudRepository<E, Long>> extends DefaultHandler {
    protected int nodeCnt = 0;

    @Async(value = "fileHandlerTaskExecutor")
    protected void process(Attributes attr, ColumnTypes columnTypes, Mapper<E> mapper, R repository, String tagName) {
        GarObjectMap data = new GarObjectMap();

        if (nodeCnt < 1) {    // skip first node
            nodeCnt++;
        } else {
            int attrCnt = attr.getLength();
            for (int i = 0; i < attrCnt; i++) {
                if (attr.getQName(i).equalsIgnoreCase(tagName)) continue;
                String name = attr.getQName(i);
                data.put(name, columnTypes.getCastValue(name, attr.getValue(i).replaceAll("'", "''")));
            }
            log.debug("Record ID = {}", Optional.ofNullable(data.get("ID")).toString());
            log.trace("\rnames: " + data.keySet());
            log.trace("\rvalues: " + data.values());

            repository.save(mapper.map(data));
        }
    }
}
