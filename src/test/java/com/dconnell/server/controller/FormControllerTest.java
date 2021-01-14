package com.dconnell.server.controller;

import com.dconnell.server.service.DefaultFormService;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class FormControllerTest {

    @Test
    void shouldPassWhenRequestingFormOptionsForSpecifiedType() {
        DefaultFormService mockFormService = mock(DefaultFormService.class);
        FormController formController = new FormController(mockFormService);
        formController.getFormOptions("type");
        verify(mockFormService).findFormOptions("type");
    }

}