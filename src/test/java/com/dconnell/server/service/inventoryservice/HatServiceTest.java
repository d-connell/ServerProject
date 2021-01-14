package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.HatFactory;
import com.dconnell.server.model.inventory.entity.Hat;
import com.dconnell.server.request.HatRequest;
import com.dconnell.server.response.inventoryresponse.HatResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.HatRepository;
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

class HatServiceTest {

    TypeRepository mockTypeRepository = mock(TypeRepository.class);
    HatRepository mockHatRepository = mock(HatRepository.class);
    HatFactory mockHatFactory = mock(HatFactory.class);
    HatService hatService = new HatService(mockTypeRepository, mockHatRepository, mockHatFactory);
    String fileName = "fileName";
    Hat mockHat = mock(Hat.class);

    @Test
    void shouldPassWhenCreatingHat() throws JsonProcessingException, JSONException {
        when(mockHatFactory.create(any(), any(), any())).thenReturn(mockHat);
        String newHatData = makeStringFromValidHatRequestJson();
        HatResponse hatResponse = hatService.create(newHatData, fileName);
        verify(mockHatRepository).save(mockHat);
        assertEquals(HttpStatus.OK, hatResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenUpdatingHat() {
        hatService.update(mockHat);
        verify(mockHatRepository).save(mockHat);
    }

    @Test
    void shouldPassWhenDeletingHat() {
        hatService.delete(mockHat);
        verify(mockHatRepository).deleteById(mockHat.getId());
    }

    @Test
    void shouldPassWhenFindingAllNotDeleted() {
        List<Hat> hats = createHatsForTesting();
        when(mockHatRepository.findAll()).thenReturn(hats);
        assertEquals(3, hats.size());
        hatService.findByDeleteStatus(false);
        assertEquals(2, hats.size());
    }

    @Test
    void shouldPassWhenFindingAllDeleted() {
        List<Hat> hats = createHatsForTesting();
        when(mockHatRepository.findAll()).thenReturn(hats);
        assertEquals(3, hats.size());
        hatService.findByDeleteStatus(true);
        assertEquals(1, hats.size());
    }

    private List<Hat> createHatsForTesting() {
        List<Hat> hats = new ArrayList<>();
        hats.add(makeTestHat(false));
        hats.add(makeTestHat(false));
        hats.add(makeTestHat(true));
        when(mockHatRepository.findAll()).thenReturn(hats);
        return hats;
    }

    private Hat makeTestHat(boolean deleteStatus) {
        Hat hat = mock(Hat.class);
        when(hat.isDeleted()).thenReturn(deleteStatus);
        return hat;
    }

    @Test
    void shouldPassWhenReadingJsonWithValidHatData() throws JsonProcessingException, JSONException {
        Hat hat = readHatFromValidJson();
        assertAll("hat",
                () -> assertEquals(new BigInteger("2"), hat.getId()),
                () -> assertEquals("Summer Hat", hat.getName()),
                () -> assertEquals(new BigDecimal(30), hat.getPrice()),
                () -> assertEquals("SMALL", hat.getSize().getName()),
                () -> assertEquals("Dawn", hat.getMaker().getName()),
                () -> assertEquals("/images/Summer Hat.png", hat.getImageUrl()),
                () -> assertFalse(hat.isDeleted())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectHatDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadHatFromJsonWithIncorrectDataType);
    }

    @Test
    void shouldPassWhenReadingJsonWithValidHatRequestData() throws JsonProcessingException, JSONException {
        HatRequest hatRequest = readHatRequestFromValidJson();
        assertAll("hatRequest",
                () -> assertEquals("Summer Hat", hatRequest.getName()),
                () -> assertEquals(new BigDecimal(30), hatRequest.getPrice()),
                () -> assertEquals("SMALL", hatRequest.getSize().getName()),
                () -> assertEquals("Dawn", hatRequest.getMaker().getName())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectHatRequestDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadHatRequestFromJsonWithIncorrectDataType);
    }

    private Hat readHatFromValidJson() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Summer Hat");
        json.put("type", makeTypeJson());
        json.put("price", 30);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Summer Hat.png");
        json.put("deleted", "false");
        String data = json.toString();
        return hatService.readEntityData(data);
    }

    private void attemptToReadHatFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Summer Hat");
        json.put("type", makeTypeJson());
        json.put("price", "thirty");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Summer Hat.png");
        json.put("deleted", "false");
        String data = json.toString();
        hatService.readEntityData(data);
    }

    private HatRequest readHatRequestFromValidJson() throws JsonProcessingException, JSONException {
        String data = makeStringFromValidHatRequestJson();
        return hatService.readRequestData(data);
    }

    private String makeStringFromValidHatRequestJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Summer Hat");
        json.put("price", 30);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        return json.toString();
    }

    private void attemptToReadHatRequestFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Summer Hat");
        json.put("price", "thirty");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        String data = json.toString();
        hatService.readRequestData(data);
    }

    private JSONObject makeTypeJson() throws JSONException {
        JSONObject type = new JSONObject();
        type.put("id", 3);
        type.put("name", "hats");
        type.put("imageUrl", "/images/Hat.png");
        type.put("category", makeCategoryJson());
        return type;
    }

    private JSONObject makeCategoryJson() throws JSONException {
        JSONObject category = new JSONObject();
        category.put("id", 2);
        category.put("name", "Accessories");
        category.put("imageUrl", "/images/Accessory.png");
        return category;
    }

    private JSONObject makeSizeJson() throws JSONException {
        JSONObject size = new JSONObject();
        size.put("id", 1);
        size.put("name", "SMALL");
        size.put("dimensions", "(circumference: 54cm)");
        return size;
    }

    private JSONObject makeMakerJson() throws JSONException {
        JSONObject maker = new JSONObject();
        maker.put("id", 1);
        maker.put("name", "Dawn");
        return maker;
    }

}