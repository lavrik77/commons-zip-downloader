package ru.lavrinenko.downloader.fias.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lavrinenko.downloader.fias.handler.AddressObjectHandler;
import ru.lavrinenko.downloader.fias.handler.AdmHierarchyHandler;
import ru.lavrinenko.downloader.fias.handler.HouseHandler;
import ru.lavrinenko.downloader.fias.handler.MunHierarchyHandler;
import ru.lavrinenko.downloader.fias.repository.AddressObjectRepository;
import ru.lavrinenko.downloader.fias.repository.AdmHierarchyRepository;
import ru.lavrinenko.downloader.fias.repository.HouseRepository;
import ru.lavrinenko.downloader.fias.repository.MunHierarchyRepository;

@RequiredArgsConstructor
@Component
public class HandlerFactory {

    private final HouseRepository houseRepository;
    private final AddressObjectRepository addressObjectRepository;
    private final AdmHierarchyRepository admHierarchyRepository;
    private final MunHierarchyRepository munHierarchyRepository;

    public HouseHandler getHouseHandler() {
        return new HouseHandler(houseRepository);
    }

    public AddressObjectHandler getAddressObjectHandler() {
        return new AddressObjectHandler(addressObjectRepository);
    }

    public AdmHierarchyHandler getAdmHierarchyHandler() {
        return new AdmHierarchyHandler(admHierarchyRepository);
    }

    public MunHierarchyHandler getMunHierarchyHandler() {
        return new MunHierarchyHandler(munHierarchyRepository);
    }
}
