package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Bag;
import com.dconnell.server.request.BagRequest;
import com.dconnell.server.response.inventoryresponse.BagResponse;
import com.dconnell.server.respository.inventoryrepository.BagRepository;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.BagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class BagControllerTest {

    private BagService mockBagService;
    private DefaultFileService mockFileService;
    private BagController bagController;
    private final MultipartFile mockFile = mock(MultipartFile.class);
    private final Bag mockBag = mock(Bag.class);
    private final BagRequest mockBagRequest = mock(BagRequest.class);
    private final BagResponse mockBagResponse = mock(BagResponse.class);
    private final List<Bag> bags = new ArrayList<>();
    private final String data = "data";
    private final String fileName = "fileName";
    private final String url = "url";

    @BeforeEach
    void initialise() throws JsonProcessingException {
        BagRepository mockBagRepository = mock(BagRepository.class);
        mockBagService = mock(BagService.class);
        mockFileService = mock(DefaultFileService.class);
        bagController = new BagController(mockBagService, mockFileService);

        when(mockBagRepository.findAll()).thenReturn(bags);
        when(mockFileService.generateRandomFileName()).thenReturn(fileName);
        when(mockBagService.readRequestData(data)).thenReturn(mockBagRequest);
        when(mockBagService.create(data, fileName)).thenReturn(mockBagResponse);
        when(mockBagService.readEntityData(data)).thenReturn(mockBag);
        when(mockBagResponse.getBag()).thenReturn(mockBag);
        when(mockBag.getId()).thenReturn(new BigInteger("1"));
        when(mockBag.getImageUrl()).thenReturn(url);
    }

    @Test
    void shouldPassWhenGettingAllNotDeleted() {
        bagController.getAllNotDeleted();
        verify(mockBagService).findByDeleteStatus(false);
    }

    @Test
    void shouldPassWhenCreatingANewBag() throws IOException {
        bagController.create(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockBagService).create(data, fileName);
    }

    @Test
    void shouldPassWhenUpdatingABagWithNewImage() throws IOException {
        bagController.update(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockBagService).readEntityData(data);
        verify(mockBagService).update(mockBag);
    }

    @Test
    void shouldPassWhenUpdatingABagWithoutChangingImage() throws IOException {
        bagController.update(data, null);
        verifyNoInteractions(mockFileService);
        verify(mockBagService).readEntityData(data);
        verify(mockBagService).update(mockBag);
    }

    @Test
    void shouldPassWhenDeletingABag() throws IOException {
        bagController.delete(mockBag);
        verify(mockFileService).delete(url);
        verify(mockBagService).delete(mockBag);
    }

}