package ru.globaltruck.downloader.fias.handler;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ru.globaltruck.downloader.fias.component.ColumnTypes;
import ru.globaltruck.downloader.fias.mapper.Mapper;
import ru.globaltruck.downloader.fias.util.GarObjectMap;

@Log4j2
@Setter
@Component
@RequiredArgsConstructor
public abstract class AbstractHandler<E, T extends CrudRepository<E, Long>> extends DefaultHandler {
    protected int nodeCnt = 0;
    protected int recordCnt = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
    }


    protected void process(Attributes attr, ColumnTypes columnTypes, Mapper<E> mapper, T repository) {
        GarObjectMap data = new GarObjectMap();

        if (nodeCnt < 1) {    // skip first node
            nodeCnt++;
        } else {
            int attrCnt = attr.getLength();
            for (int i = 0; i < attrCnt; i++) {
                if ("OBJECT".equalsIgnoreCase(attr.getQName(i))) continue;
                String name = attr.getQName(i);
                data.put(name, columnTypes.getCastValue(name, attr.getValue(i).replaceAll("'", "''")));
            }

            recordCnt++;
            log.trace("\rprocessed: " + recordCnt);
            log.trace("\rnames: " + data.keySet());
            log.trace("\rvalues: " + data.values());

            repository.save(mapper.map(data));
            System.out.print("Record\u00A0No\u00A0" + recordCnt + "\t");
        }
    }
}
