package com.dconnell.server.controller;

import com.dconnell.server.controller.controllertools.*;
import com.dconnell.server.model.enums.Type;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.service.inventoryservice.QuiltService;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    private final ServiceFinder mockServiceFinder = mock(ServiceFinder.class);
    private final TypeRepository mockTypeRepository = mock(TypeRepository.class);
    private final AdminController adminController = new AdminController(mockServiceFinder, mockTypeRepository);
    private final QuiltService mockQuiltService = mock(QuiltService.class);

    @Test
    void shouldPassWhenGettingTypes() {
        adminController.getTypes();
        verify(mockTypeRepository).findAll();
    }

    @Test
    void shouldPassWhenGettingDeletedQuilts() {
        when(mockServiceFinder.findService(Type.QUILTS.getLabel())).thenReturn(mockQuiltService);
        adminController.getAllDeleted(Type.QUILTS.getLabel());
        verify(mockServiceFinder).findService(Type.QUILTS.getLabel());
        verify(mockQuiltService).findByDeleteStatus(true);
    }

    @Test
    void shouldPassToConfirmAdminActionsCreatedAsExpected() {
        List<AdminAction> actions = adminController.getAdminActions();
        assertAll("action",
                () ->assertEquals("Review deletions", actions.get(0).getDisplayText()),
                () ->assertEquals("deletions", actions.get(0).getLinkText()));
    }

}