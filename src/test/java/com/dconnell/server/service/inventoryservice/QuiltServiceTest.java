package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.QuiltFactory;
import com.dconnell.server.model.inventory.entity.Quilt;
import com.dconnell.server.request.QuiltRequest;
import com.dconnell.server.response.inventoryresponse.QuiltResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.QuiltRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuiltServiceTest {

    TypeRepository mockTypeRepository = mock(TypeRepository.class);
    QuiltRepository mockQuiltRepository = mock(QuiltRepository.class);
    QuiltFactory mockQuiltFactory = mock(QuiltFactory.class);
    QuiltService quiltService = new QuiltService(mockTypeRepository, mockQuiltRepository, mockQuiltFactory);
    String fileName = "fileName";
    Quilt mockQuilt = mock(Quilt.class);

    @Test
    void shouldPassWhenCreatingQuilt() throws JsonProcessingException, JSONException {
        when(mockQuiltFactory.create(any(), any(), any())).thenReturn(mockQuilt);
        String newQuiltData = makeStringFromValidQuiltRequestJson();
        QuiltResponse quiltResponse = quiltService.create(newQuiltData, fileName);
        verify(mockQuiltRepository).save(mockQuilt);
        assertEquals(HttpStatus.OK, quiltResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenUpdatingQuilt() {
        quiltService.update(mockQuilt);
        verify(mockQuiltRepository).save(mockQuilt);
    }

    @Test
    void shouldPassWhenDeletingQuilt() {
        quiltService.delete(mockQuilt);
        verify(mockQuiltRepository).deleteById(mockQuilt.getId());
    }

    @Test
    void shouldPassWhenFindingAllNotDeleted() {
        List<Quilt> quilts = createQuiltsForTesting();
        when(mockQuiltRepository.findAll()).thenReturn(quilts);
        assertEquals(3, quilts.size());
        quiltService.findByDeleteStatus(false);
        assertEquals(2, quilts.size());
    }

    @Test
    void shouldPassWhenFindingAllDeleted() {
        List<Quilt> quilts = createQuiltsForTesting();
        when(mockQuiltRepository.findAll()).thenReturn(quilts);
        assertEquals(3, quilts.size());
        quiltService.findByDeleteStatus(true);
        assertEquals(1, quilts.size());
    }

    private List<Quilt> createQuiltsForTesting() {
        List<Quilt> quilts = new ArrayList<>();
        quilts.add(makeTestQuilt(false));
        quilts.add(makeTestQuilt(false));
        quilts.add(makeTestQuilt(true));
        when(mockQuiltRepository.findAll()).thenReturn(quilts);
        return quilts;
    }

    private Quilt makeTestQuilt(boolean deleteStatus) {
        Quilt quilt = mock(Quilt.class);
        when(quilt.isDeleted()).thenReturn(deleteStatus);
        return quilt;
    }

    @Test
    void shouldPassWhenReadingJsonWithValidQuiltData() throws JsonProcessingException, JSONException {
        Quilt quilt = readQuiltFromValidJson();
        assertAll("quilt",
                () -> assertEquals(new BigInteger("2"), quilt.getId()),
                () -> assertEquals("Winter Quilt", quilt.getName()),
                () -> assertEquals(new BigDecimal(500), quilt.getPrice()),
                () -> assertEquals("SMALL", quilt.getSize().getName()),
                () -> assertEquals("Dawn", quilt.getMaker().getName()),
                () -> assertEquals("/images/Winter Quilt.png", quilt.getImageUrl()),
                () -> assertFalse(quilt.isDeleted())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectQuiltDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadQuiltFromJsonWithIncorrectDataType);
    }

    @Test
    void shouldPassWhenReadingJsonWithValidQuiltRequestData() throws JsonProcessingException, JSONException {
        QuiltRequest quiltRequest = readQuiltRequestFromValidJson();
        assertAll("quiltRequest",
                () -> assertEquals("Winter Quilt", quiltRequest.getName()),
                () -> assertEquals(new BigDecimal(500), quiltRequest.getPrice()),
                () -> assertEquals("SMALL", quiltRequest.getSize().getName()),
                () -> assertEquals("Dawn", quiltRequest.getMaker().getName())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectQuiltRequestDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadQuiltRequestFromJsonWithIncorrectDataType);
    }

    private Quilt readQuiltFromValidJson() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Winter Quilt");
        json.put("type", makeTypeJson());
        json.put("price", 500);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Winter Quilt.png");
        json.put("deleted", "false");
        String data = json.toString();
        return quiltService.readEntityData(data);
    }

    private void attemptToReadQuiltFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Winter Quilt");
        json.put("type", makeTypeJson());
        json.put("price", "five hundred");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Winter Quilt.png");
        json.put("deleted", "false");
        String data = json.toString();
        quiltService.readEntityData(data);
    }

    private QuiltRequest readQuiltRequestFromValidJson() throws JsonProcessingException, JSONException {
        String data = makeStringFromValidQuiltRequestJson();
        return quiltService.readRequestData(data);
    }

    private String makeStringFromValidQuiltRequestJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Winter Quilt");
        json.put("price", 500);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        return json.toString();
    }

    private void attemptToReadQuiltRequestFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Winter Quilt");
        json.put("price", "five hundred");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        String data = json.toString();
        quiltService.readRequestData(data);
    }

    private JSONObject makeTypeJson() throws JSONException {
        JSONObject type = new JSONObject();
        type.put("id", 1);
        type.put("name", "quilts");
        type.put("imageUrl", "/images/Quilt.png");
        type.put("category", makeCategoryJson());
        return type;
    }

    private JSONObject makeCategoryJson() throws JSONException {
        JSONObject category = new JSONObject();
        category.put("id", 1);
        category.put("name", "Covers");
        category.put("imageUrl", "/images/Cover.png");
        return category;
    }

    private JSONObject makeSizeJson() throws JSONException {
        JSONObject size = new JSONObject();
        size.put("id", 1);
        size.put("name", "SMALL");
        size.put("dimensions", "(width: 40cm, height: 40cm, depth: 10cm)");
        return size;
    }

    private JSONObject makeMakerJson() throws JSONException {
        JSONObject maker = new JSONObject();
        maker.put("id", 1);
        maker.put("name", "Dawn");
        return maker;
    }

}