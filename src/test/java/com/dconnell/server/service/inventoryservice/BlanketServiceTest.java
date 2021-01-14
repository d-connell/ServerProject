package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.BlanketFactory;
import com.dconnell.server.model.inventory.entity.Blanket;
import com.dconnell.server.request.BlanketRequest;
import com.dconnell.server.response.inventoryresponse.BlanketResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.BlanketRepository;
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

class BlanketServiceTest {

    TypeRepository mockTypeRepository = mock(TypeRepository.class);
    BlanketRepository mockBlanketRepository = mock(BlanketRepository.class);
    BlanketFactory mockBlanketFactory = mock(BlanketFactory.class);
    BlanketService blanketService = new BlanketService(mockTypeRepository, mockBlanketRepository, mockBlanketFactory);
    String fileName = "fileName";
    Blanket mockBlanket = mock(Blanket.class);

    @Test
    void shouldPassWhenCreatingBlanket() throws JsonProcessingException, JSONException {
        when(mockBlanketFactory.create(any(), any(), any())).thenReturn(mockBlanket);
        String newBlanketData = makeStringFromValidBlanketRequestJson();
        BlanketResponse blanketResponse = blanketService.create(newBlanketData, fileName);
        verify(mockBlanketRepository).save(mockBlanket);
        assertEquals(HttpStatus.OK, blanketResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenUpdatingBlanket() {
        blanketService.update(mockBlanket);
        verify(mockBlanketRepository).save(mockBlanket);
    }

    @Test
    void shouldPassWhenDeletingBlanket() {
        blanketService.delete(mockBlanket);
        verify(mockBlanketRepository).deleteById(mockBlanket.getId());
    }

    @Test
    void shouldPassWhenFindingAllNotDeleted() {
        List<Blanket> blankets = createBlanketsForTesting();
        when(mockBlanketRepository.findAll()).thenReturn(blankets);
        assertEquals(3, blankets.size());
        blanketService.findByDeleteStatus(false);
        assertEquals(2, blankets.size());
    }

    @Test
    void shouldPassWhenFindingAllDeleted() {
        List<Blanket> blankets = createBlanketsForTesting();
        when(mockBlanketRepository.findAll()).thenReturn(blankets);
        assertEquals(3, blankets.size());
        blanketService.findByDeleteStatus(true);
        assertEquals(1, blankets.size());
    }

    private List<Blanket> createBlanketsForTesting() {
        List<Blanket> blankets = new ArrayList<>();
        blankets.add(makeTestBlanket(false));
        blankets.add(makeTestBlanket(false));
        blankets.add(makeTestBlanket(true));
        when(mockBlanketRepository.findAll()).thenReturn(blankets);
        return blankets;
    }

    private Blanket makeTestBlanket(boolean deleteStatus) {
        Blanket blanket = mock(Blanket.class);
        when(blanket.isDeleted()).thenReturn(deleteStatus);
        return blanket;
    }

    @Test
    void shouldPassWhenReadingJsonWithValidBlanketData() throws JsonProcessingException, JSONException {
        Blanket blanket = readBlanketFromValidJson();
        assertAll("blanket",
                () -> assertEquals(new BigInteger("2"), blanket.getId()),
                () -> assertEquals("Baby Blanket", blanket.getName()),
                () -> assertEquals(new BigDecimal(200), blanket.getPrice()),
                () -> assertEquals("SMALL", blanket.getSize().getName()),
                () -> assertEquals("Dawn", blanket.getMaker().getName()),
                () -> assertEquals("/images/Baby Blanket.png", blanket.getImageUrl()),
                () -> assertFalse(blanket.isDeleted())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectBlanketDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadBlanketFromJsonWithIncorrectDataType);
    }

    @Test
    void shouldPassWhenReadingJsonWithValidBlanketRequestData() throws JsonProcessingException, JSONException {
        BlanketRequest blanketRequest = readBlanketRequestFromValidJson();
        assertAll("blanketRequest",
                () -> assertEquals("Baby Blanket", blanketRequest.getName()),
                () -> assertEquals(new BigDecimal(200), blanketRequest.getPrice()),
                () -> assertEquals("SMALL", blanketRequest.getSize().getName()),
                () -> assertEquals("Dawn", blanketRequest.getMaker().getName())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectBlanketRequestDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadBlanketRequestFromJsonWithIncorrectDataType);
    }

    private Blanket readBlanketFromValidJson() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Baby Blanket");
        json.put("type", makeTypeJson());
        json.put("price", 200);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Baby Blanket.png");
        json.put("deleted", "false");
        String data = json.toString();
        return blanketService.readEntityData(data);
    }

    private void attemptToReadBlanketFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Baby Blanket");
        json.put("type", makeTypeJson());
        json.put("price", "two hundred");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Baby Blanket.png");
        json.put("deleted", "false");
        String data = json.toString();
        blanketService.readEntityData(data);
    }

    private BlanketRequest readBlanketRequestFromValidJson() throws JsonProcessingException, JSONException {
        String data = makeStringFromValidBlanketRequestJson();
        return blanketService.readRequestData(data);
    }

    private String makeStringFromValidBlanketRequestJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Baby Blanket");
        json.put("price", 200);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        return json.toString();
    }

    private void attemptToReadBlanketRequestFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Baby Blanket");
        json.put("price", "two hundred");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        String data = json.toString();
        blanketService.readRequestData(data);
    }

    private JSONObject makeTypeJson() throws JSONException {
        JSONObject type = new JSONObject();
        type.put("id", 1);
        type.put("name", "blankets");
        type.put("imageUrl", "/images/Blanket.png");
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
        size.put("dimensions", "(width: 92cm, height: 188cm)");
        return size;
    }

    private JSONObject makeMakerJson() throws JSONException {
        JSONObject maker = new JSONObject();
        maker.put("id", 1);
        maker.put("name", "Dawn");
        return maker;
    }

}