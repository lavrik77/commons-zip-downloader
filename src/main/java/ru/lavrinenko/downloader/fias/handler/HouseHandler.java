package ru.lavrinenko.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.lavrinenko.downloader.fias.component.HouseColumnTypes;
import ru.lavrinenko.downloader.fias.mapper.HouseMapper;
import ru.lavrinenko.downloader.fias.repository.HouseRepository;
import ru.lavrinenko.downloader.fias.repository.entity.HouseEntity;

public class HouseHandler extends AbstractHandler<HouseEntity, HouseRepository> {

    private final HouseRepository repository;

    public HouseHandler(HouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        if (qName.equalsIgnoreCase("HOUSE")) {
            HouseColumnTypes aoColumnTypes = new HouseColumnTypes();
            HouseMapper mapper = new HouseMapper();
            process(attr, aoColumnTypes, mapper, repository);
        }
    }
}
