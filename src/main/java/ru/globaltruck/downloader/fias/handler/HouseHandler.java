package ru.globaltruck.downloader.fias.handler;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import ru.globaltruck.downloader.fias.component.HouseColumnTypes;
import ru.globaltruck.downloader.fias.mapper.HouseMapper;
import ru.globaltruck.downloader.fias.repository.HouseRepository;

@Log4j2
@Setter
@Component
@RequiredArgsConstructor
public class HouseHandler extends AbstractHandler {
    private final HouseMapper mapper;
    private final HouseRepository repository;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        HouseColumnTypes aoColumnTypes = new HouseColumnTypes();
        process(attr, aoColumnTypes, mapper, repository);
    }
}
