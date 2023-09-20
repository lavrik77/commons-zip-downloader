package ru.lavrinenko.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.lavrinenko.downloader.fias.component.AddressObjectColumnTypes;
import ru.lavrinenko.downloader.fias.mapper.AddressObjectMapper;
import ru.lavrinenko.downloader.fias.repository.AddressObjectRepository;
import ru.lavrinenko.downloader.fias.repository.entity.AddressObjectEntity;

public class AddressObjectHandler extends AbstractHandler<AddressObjectEntity, AddressObjectRepository> {

    private final AddressObjectRepository repository;

    public AddressObjectHandler(AddressObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        if (qName.equalsIgnoreCase("OBJECT")) {
            AddressObjectColumnTypes aoColumnTypes = new AddressObjectColumnTypes();
            AddressObjectMapper mapper = new AddressObjectMapper();
            process(attr, aoColumnTypes, mapper, repository);
        }
    }
}
