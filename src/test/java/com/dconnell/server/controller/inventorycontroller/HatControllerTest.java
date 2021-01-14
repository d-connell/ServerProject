package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Hat;
import com.dconnell.server.request.HatRequest;
import com.dconnell.server.response.inventoryresponse.HatResponse;
import com.dconnell.server.respository.inventoryrepository.HatRepository;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.HatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class HatControllerTest {

    private HatService mockHatService;
    private DefaultFileService mockFileService;
    private HatController hatController;
    private final MultipartFile mockFile = mock(MultipartFile.class);
    private final Hat mockHat = mock(Hat.class);
    private final HatRequest mockHatRequest = mock(HatRequest.class);
    private final HatResponse mockHatResponse = mock(HatResponse.class);
    private final List<Hat> hats = new ArrayList<>();
    private final String data = "data";
    private final String fileName = "fileName";
    private final String url = "url";

    @BeforeEach
    void initialise() throws JsonProcessingException {
        HatRepository mockHatRepository = mock(HatRepository.class);
        mockHatService = mock(HatService.class);
        mockFileService = mock(DefaultFileService.class);
        hatController = new HatController(mockHatService, mockFileService);

        when(mockHatRepository.findAll()).thenReturn(hats);
        when(mockFileService.generateRandomFileName()).thenReturn(fileName);
        when(mockHatService.readRequestData(data)).thenReturn(mockHatRequest);
        when(mockHatService.create(data, fileName)).thenReturn(mockHatResponse);
        when(mockHatService.readEntityData(data)).thenReturn(mockHat);
        when(mockHatResponse.getHat()).thenReturn(mockHat);
        when(mockHat.getId()).thenReturn(new BigInteger("1"));
        when(mockHat.getImageUrl()).thenReturn(url);
    }

    @Test
    void shouldPassWhenGettingAllNotDeleted() {
        hatController.getAllNotDeleted();
        verify(mockHatService).findByDeleteStatus(false);
    }

    @Test
    void shouldPassWhenCreatingANewHat() throws IOException {
        hatController.create(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockHatService).create(data, fileName);
    }

    @Test
    void shouldPassWhenUpdatingAHatWithNewImage() throws IOException {
        hatController.update(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockHatService).readEntityData(data);
        verify(mockHatService).update(mockHat);
    }

    @Test
    void shouldPassWhenUpdatingAHatWithoutChangingImage() throws IOException {
        hatController.update(data, null);
        verifyNoInteractions(mockFileService);
        verify(mockHatService).readEntityData(data);
        verify(mockHatService).update(mockHat);
    }

    @Test
    void shouldPassWhenDeletingAHat() throws IOException {
        hatController.delete(mockHat);
        verify(mockFileService).delete(url);
        verify(mockHatService).delete(mockHat);
    }

}