package ru.globaltruck.downloader.fias.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.globaltruck.downloader.fias.handler.AddressObjectHandler;
import ru.globaltruck.downloader.fias.handler.AdmHierarchyHandler;
import ru.globaltruck.downloader.fias.handler.HouseHandler;
import ru.globaltruck.downloader.fias.handler.MunHierarchyHandler;
import ru.globaltruck.downloader.fias.repository.AddressObjectRepository;
import ru.globaltruck.downloader.fias.repository.AdmHierarchyRepository;
import ru.globaltruck.downloader.fias.repository.HouseRepository;
import ru.globaltruck.downloader.fias.repository.MunHierarchyRepository;

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
