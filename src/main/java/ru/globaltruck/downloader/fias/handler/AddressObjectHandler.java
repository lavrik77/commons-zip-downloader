package ru.globaltruck.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.globaltruck.downloader.fias.component.AddressObjectColumnTypes;
import ru.globaltruck.downloader.fias.mapper.AddressObjectMapper;
import ru.globaltruck.downloader.fias.repository.AddressObjectRepository;
import ru.globaltruck.downloader.fias.repository.entity.AddressObjectEntity;

public class AddressObjectHandler extends AbstractHandler<AddressObjectEntity, AddressObjectRepository> {

    private final AddressObjectRepository repository;

    public AddressObjectHandler(AddressObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        AddressObjectColumnTypes aoColumnTypes = new AddressObjectColumnTypes();
        AddressObjectMapper mapper = new AddressObjectMapper();
        process(attr, aoColumnTypes, mapper, repository, "OBJECT");
    }
}
