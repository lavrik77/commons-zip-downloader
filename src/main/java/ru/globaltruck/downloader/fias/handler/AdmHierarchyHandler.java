package ru.globaltruck.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.globaltruck.downloader.fias.component.AdmHierarchyColumnTypes;
import ru.globaltruck.downloader.fias.handler.AbstractHandler;
import ru.globaltruck.downloader.fias.mapper.AdmHierarhyMapper;
import ru.globaltruck.downloader.fias.repository.AdmHierarchyRepository;
import ru.globaltruck.downloader.fias.repository.entity.AdmHierarchyEntity;

public class AdmHierarchyHandler extends AbstractHandler<AdmHierarchyEntity, AdmHierarchyRepository> {

    private final AdmHierarchyRepository repository;

    public AdmHierarchyHandler(AdmHierarchyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        AdmHierarchyColumnTypes aoColumnTypes = new AdmHierarchyColumnTypes();
        AdmHierarhyMapper mapper = new AdmHierarhyMapper();
        process(attr, aoColumnTypes, mapper, repository, "OBJECT");
    }
}
