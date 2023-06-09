package ru.globaltruck.downloader.fias.handler;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import ru.globaltruck.downloader.fias.component.AddressObjectColumnTypes;
import ru.globaltruck.downloader.fias.mapper.AddressObjectMapper;
import ru.globaltruck.downloader.fias.repository.AddressObjectRepository;
import ru.globaltruck.downloader.fias.repository.entity.AddressObjectEntity;

@Log4j2
@Setter
@Component
@RequiredArgsConstructor
public class AddressObjectHandler extends AbstractHandler<AddressObjectEntity, AddressObjectRepository> {
    private final AddressObjectMapper mapper;
    private final AddressObjectRepository repository;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        AddressObjectColumnTypes aoColumnTypes = new AddressObjectColumnTypes();
        process(attr, aoColumnTypes, mapper, repository);
    }
}
