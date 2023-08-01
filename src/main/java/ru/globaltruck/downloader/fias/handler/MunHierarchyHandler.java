package ru.globaltruck.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.globaltruck.downloader.fias.component.MunHierarchyColumnTypes;
import ru.globaltruck.downloader.fias.handler.AbstractHandler;
import ru.globaltruck.downloader.fias.mapper.MunHierarhyMapper;
import ru.globaltruck.downloader.fias.repository.MunHierarchyRepository;
import ru.globaltruck.downloader.fias.repository.entity.MunHierarchyEntity;

public class MunHierarchyHandler extends AbstractHandler<MunHierarchyEntity, MunHierarchyRepository> {

    private final MunHierarchyRepository repository;

    public MunHierarchyHandler(MunHierarchyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        MunHierarchyColumnTypes aoColumnTypes = new MunHierarchyColumnTypes();
        MunHierarhyMapper mapper = new MunHierarhyMapper();
        process(attr, aoColumnTypes, mapper, repository, "OBJECT");
    }
}
