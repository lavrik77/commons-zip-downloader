package ru.globaltruck.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.globaltruck.downloader.fias.component.HouseColumnTypes;
import ru.globaltruck.downloader.fias.mapper.HouseMapper;
import ru.globaltruck.downloader.fias.repository.HouseRepository;
import ru.globaltruck.downloader.fias.repository.entity.HouseEntity;

public class HouseHandler extends AbstractHandler<HouseEntity, HouseRepository> {

    private final HouseRepository repository;

    public HouseHandler(HouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        HouseColumnTypes aoColumnTypes = new HouseColumnTypes();
        HouseMapper mapper = new HouseMapper();
        process(attr, aoColumnTypes, mapper, repository, "HOUSE");
    }
}
