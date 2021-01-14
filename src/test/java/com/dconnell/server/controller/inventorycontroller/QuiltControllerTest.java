package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Quilt;
import com.dconnell.server.request.QuiltRequest;
import com.dconnell.server.response.inventoryresponse.QuiltResponse;
import com.dconnell.server.respository.inventoryrepository.QuiltRepository;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.QuiltService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class QuiltControllerTest {

    private QuiltService mockQuiltService;
    private DefaultFileService mockFileService;
    private QuiltController quiltController;
    private final MultipartFile mockFile = mock(MultipartFile.class);
    private final Quilt mockQuilt = mock(Quilt.class);
    private final QuiltRequest mockQuiltRequest = mock(QuiltRequest.class);
    private final QuiltResponse mockQuiltResponse = mock(QuiltResponse.class);
    private final List<Quilt> quilts = new ArrayList<>();
    private final String data = "data";
    private final String fileName = "fileName";
    private final String url = "url";

    @BeforeEach
    void initialise() throws JsonProcessingException {
        QuiltRepository mockQuiltRepository = mock(QuiltRepository.class);
        mockQuiltService = mock(QuiltService.class);
        mockFileService = mock(DefaultFileService.class);
        quiltController = new QuiltController(mockQuiltService, mockFileService);

        when(mockQuiltRepository.findAll()).thenReturn(quilts);
        when(mockFileService.generateRandomFileName()).thenReturn(fileName);
        when(mockQuiltService.readRequestData(data)).thenReturn(mockQuiltRequest);
        when(mockQuiltService.create(data, fileName)).thenReturn(mockQuiltResponse);
        when(mockQuiltService.readEntityData(data)).thenReturn(mockQuilt);
        when(mockQuiltResponse.getQuilt()).thenReturn(mockQuilt);
        when(mockQuilt.getId()).thenReturn(new BigInteger("1"));
        when(mockQuilt.getImageUrl()).thenReturn(url);
    }

    @Test
    void shouldPassWhenGettingAllNotDeleted() {
        quiltController.getAllNotDeleted();
        verify(mockQuiltService).findByDeleteStatus(false);
    }

    @Test
    void shouldPassWhenCreatingANewQuilt() throws IOException {
        quiltController.create(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockQuiltService).create(data, fileName);
    }

    @Test
    void shouldPassWhenUpdatingAQuiltWithNewImage() throws IOException {
        quiltController.update(data, mockFile);
        verify(mockFileService).save(mockFile, fileName);
        verify(mockQuiltService).readEntityData(data);
        verify(mockQuiltService).update(mockQuilt);
    }

    @Test
    void shouldPassWhenUpdatingAQuiltWithoutChangingImage() throws IOException {
        quiltController.update(data, null);
        verifyNoInteractions(mockFileService);
        verify(mockQuiltService).readEntityData(data);
        verify(mockQuiltService).update(mockQuilt);
    }

    @Test
    void shouldPassWhenDeletingAQuilt() throws IOException {
        quiltController.delete(mockQuilt);
        verify(mockFileService).delete(url);
        verify(mockQuiltService).delete(mockQuilt);
    }

}