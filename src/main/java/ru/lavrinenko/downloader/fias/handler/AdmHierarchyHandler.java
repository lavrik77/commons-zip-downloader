package ru.lavrinenko.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.lavrinenko.downloader.fias.component.AdmHierarchyColumnTypes;
import ru.lavrinenko.downloader.fias.mapper.AdmHierarhyMapper;
import ru.lavrinenko.downloader.fias.repository.AdmHierarchyRepository;
import ru.lavrinenko.downloader.fias.repository.entity.AdmHierarchyEntity;

public class AdmHierarchyHandler extends AbstractHandler<AdmHierarchyEntity, AdmHierarchyRepository> {

    private final AdmHierarchyRepository repository;

    public AdmHierarchyHandler(AdmHierarchyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        if (qName.equalsIgnoreCase("ITEM")) {
            AdmHierarchyColumnTypes aoColumnTypes = new AdmHierarchyColumnTypes();
            AdmHierarhyMapper mapper = new AdmHierarhyMapper();
            process(attr, aoColumnTypes, mapper, repository);
        }
    }
}
