package com.dconnell.server.controller;

import com.dconnell.server.controller.controllertools.ServiceFinder;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.respository.CategoryRepository;
import com.dconnell.server.respository.inventoryrepository.*;
import com.dconnell.server.service.TypeService;
import com.dconnell.server.service.inventoryservice.InventoryService;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewControllerTest {

    private static final ServiceFinder mockServiceFinder = mock(ServiceFinder.class);
    private static final InventoryService mockService = mock(InventoryService.class);
    private static final CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);
    private static final TypeService mockTypeService = mock(TypeService.class);
    private static final BagRepository mockBagRepository = mock(BagRepository.class);
    private static final BlanketRepository mockBlanketRepository = mock(BlanketRepository.class);
    private static final HatRepository mockHatRepository = mock(HatRepository.class);
    private static final QuiltRepository mockQuiltRepository = mock(QuiltRepository.class);

    private static final ViewController viewController = new ViewController(mockServiceFinder, mockCategoryRepository,
            mockTypeService, mockBagRepository, mockBlanketRepository, mockHatRepository, mockQuiltRepository);

    @Test
    void shouldPassWhenGettingCategories() {
        viewController.getCategories();
        verify(mockCategoryRepository).findAll();
    }

    @Test
    void shouldPassWhenGettingValidCategoryTypes() {
        String categoryName = "Covers";
        viewController.getTypesForCategory(categoryName);
        verify(mockTypeService).findTypesForCategory(categoryName);
    }

    @Test
    void shouldPassWhenGettingItemsForType() throws NullPointerException {
        when(mockServiceFinder.findService("type")).thenReturn(mockService);
        viewController.getAllItemsForType("type");
        verify(mockService).findByDeleteStatus(false);
    }

    @Test
    void shouldPassWhenGettingDetailsForBag() throws NullPointerException {
        viewController.getDetails(Type.BAGS.getLabel(), "1");
        verify(mockBagRepository).findById(new BigInteger("1"));
    }

    @Test
    void shouldPassWhenGettingDetailsForBlanket() throws NullPointerException {
        viewController.getDetails(Type.BLANKETS.getLabel(), "1");
        verify(mockBlanketRepository).findById(new BigInteger("1"));
    }

    @Test
    void shouldPassWhenGettingDetailsForHat() throws NullPointerException {
        viewController.getDetails(Type.HATS.getLabel(), "1");
        verify(mockHatRepository).findById(new BigInteger("1"));
    }

    @Test
    void shouldPassWhenGettingDetailsForQuilt() throws NullPointerException {
        viewController.getDetails(Type.QUILTS.getLabel(), "1");
        verify(mockQuiltRepository).findById(new BigInteger("1"));
    }

    @Test
    void shouldThrowErrorWhenGettingDetailsForNonExistentType() {
        assertThrows(NullPointerException.class, this::getDetailsWhenTypeNotFound);
    }

    private void getDetailsWhenTypeNotFound() throws NullPointerException {
        viewController.getDetails("nonsense", "1");
    }

}