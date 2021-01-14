package com.dconnell.server.controller.controllertools;

import com.dconnell.server.factory.*;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.*;
import com.dconnell.server.service.inventoryservice.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ServiceFinderTest {

    private static final TypeRepository typeRepository = mock(TypeRepository.class);
    private static final BagRepository bagRepository = mock(BagRepository.class);
    private static final BlanketRepository blanketRepository = mock(BlanketRepository.class);
    private static final HatRepository hatRepository = mock(HatRepository.class);
    private static final QuiltRepository quiltRepository = mock(QuiltRepository.class);
    private static final BagFactory bagFactory = mock(BagFactory.class);
    private static final BlanketFactory blanketFactory = mock(BlanketFactory.class);
    private static final HatFactory hatFactory = mock(HatFactory.class);
    private static final QuiltFactory quiltFactory = mock(QuiltFactory.class);

    private static final BagService bagService = new BagService(typeRepository, bagRepository, bagFactory);
    private static final BlanketService blanketService = new BlanketService(typeRepository, blanketRepository, blanketFactory);
    private static final HatService hatService = new HatService(typeRepository, hatRepository, hatFactory);
    private static final QuiltService quiltService = new QuiltService(typeRepository, quiltRepository, quiltFactory);
    private static final ServiceFinder serviceFinder = new ServiceFinder(bagService, blanketService, hatService, quiltService);

    @Test
    void shouldPassWhenGettingBagService() throws NullPointerException {
        InventoryService service = serviceFinder.findService(Type.BAGS.getLabel());
        assertSame(BagService.class, service.getClass());
    }

    @Test
    void shouldPassWhenGettingBlanketService() throws NullPointerException {
        InventoryService service = serviceFinder.findService(Type.BLANKETS.getLabel());
        assertSame(BlanketService.class, service.getClass());
    }

    @Test
    void shouldPassWhenGettingHatService() throws NullPointerException {
        InventoryService service = serviceFinder.findService(Type.HATS.getLabel());
        assertSame(HatService.class, service.getClass());
    }

    @Test
    void shouldPassWhenGettingQuiltService() throws NullPointerException {
        InventoryService service = serviceFinder.findService(Type.QUILTS.getLabel());
        assertSame(QuiltService.class, service.getClass());
    }

    @Test
    void shouldThrowErrorWhenTypeNotRecognised() {
        assertThrows(NullPointerException.class, this::getTestService);
    }

    private void getTestService() throws NullPointerException {
        serviceFinder.findService("nonsense");
    }

}