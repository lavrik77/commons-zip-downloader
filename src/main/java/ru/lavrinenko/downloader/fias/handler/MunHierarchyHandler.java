package ru.lavrinenko.downloader.fias.handler;

import org.xml.sax.Attributes;
import ru.lavrinenko.downloader.fias.component.MunHierarchyColumnTypes;
import ru.lavrinenko.downloader.fias.mapper.MunHierarhyMapper;
import ru.lavrinenko.downloader.fias.repository.MunHierarchyRepository;
import ru.lavrinenko.downloader.fias.repository.entity.MunHierarchyEntity;

public class MunHierarchyHandler extends AbstractHandler<MunHierarchyEntity, MunHierarchyRepository> {

    private final MunHierarchyRepository repository;

    public MunHierarchyHandler(MunHierarchyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        if (qName.equalsIgnoreCase("ITEM")) {
            MunHierarchyColumnTypes aoColumnTypes = new MunHierarchyColumnTypes();
            MunHierarhyMapper mapper = new MunHierarhyMapper();
            process(attr, aoColumnTypes, mapper, repository);
        }
    }
}
