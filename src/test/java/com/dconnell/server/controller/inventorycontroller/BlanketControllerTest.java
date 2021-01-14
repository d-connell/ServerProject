package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Blanket;
import com.dconnell.server.request.BlanketRequest;
import com.dconnell.server.response.inventoryresponse.BlanketResponse;
import com.dconnell.server.respository.inventoryrepository.BlanketRepository;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.BlanketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class BlanketControllerTest {

    private BlanketService mockBlanketService;
    private DefaultFileService mockFileService;
    private BlanketController blanketController;
    private final MultipartFile mockFile = mock(MultipartFile.class);
    private final Blanket mockBlanket = mock(Blanket.class);
    private final BlanketRequest mockBlanketRequest = mock(BlanketRequest.class);
    private final BlanketResponse mockBlanketResponse = mock(BlanketResponse.class);
    private final List<Blanket> blankets = new ArrayList<>();
    private final String data = "data";
    private final String fileName = "fileName";
    private final String url = "url";

    @BeforeEach
    void initialise() throws JsonProcessingException {
        BlanketRepository mockBlanketRepository = mock(BlanketRepository.class);
        mockBlanketService = mock(BlanketService.class);
        mockFileService = mock(DefaultFileService.class);
        blanketController = new BlanketController(mockBlanketService, mockFileService);

        when(mockBlanketRepository.findAll()).thenReturn(blankets);
        when(mockFileService.generateRandomFileName()).thenReturn(fileName);
        when(mockBlanketService.readRequestData(data)).thenReturn(mockBlanketRequest);
        when(mockBlanketService.create(data, fileName)).thenReturn(mockBlanketResponse);
        when(mockBlanketService.readEntityData(data)).thenReturn(mockBlanket);
        when(mockBlanketResponse.getBlanket()).thenReturn(mockBlanket);
        when(mockBlanket.getId()).thenReturn(new BigInteger("1"));
        when(mockBlanket.getImageUrl()).thenReturn(url);
    }

    @Test
    void shouldPassWhenGettingAllNotDeleted() {
        blanketController.getAllNotDeleted();
        verify(mockBlanketService).findByDeleteStatus(false);
    }

    @Test
    void shouldPassWhenCreatingANewBlanket() throws IOException {
        blanketController.create(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockBlanketService).create(data, fileName);
    }

    @Test
    void shouldPassWhenUpdatingABlanketWithNewImage() throws IOException {
        blanketController.update(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockBlanketService).readEntityData(data);
        verify(mockBlanketService).update(mockBlanket);
    }

    @Test
    void shouldPassWhenUpdatingABlanketWithoutChangingImage() throws IOException {
        blanketController.update(data, null);
        verifyNoInteractions(mockFileService);
        verify(mockBlanketService).readEntityData(data);
        verify(mockBlanketService).update(mockBlanket);
    }

    @Test
    void shouldPassWhenDeletingABlanket() throws IOException {
        blanketController.delete(mockBlanket);
        verify(mockFileService).delete(url);
        verify(mockBlanketService).delete(mockBlanket);
    }

}