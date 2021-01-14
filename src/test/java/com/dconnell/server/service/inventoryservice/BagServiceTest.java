package com.dconnell.server.service.inventoryservice;

import com.dconnell.server.factory.BagFactory;
import com.dconnell.server.model.inventory.entity.Bag;
import com.dconnell.server.request.BagRequest;
import com.dconnell.server.response.inventoryresponse.BagResponse;
import com.dconnell.server.respository.TypeRepository;
import com.dconnell.server.respository.inventoryrepository.BagRepository;
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

class BagServiceTest {

    TypeRepository mockTypeRepository = mock(TypeRepository.class);
    BagRepository mockBagRepository = mock(BagRepository.class);
    BagFactory mockBagFactory = mock(BagFactory.class);
    BagService bagService = new BagService(mockTypeRepository, mockBagRepository, mockBagFactory);
    String fileName = "fileName";
    Bag mockBag = mock(Bag.class);

    @Test
    void shouldPassWhenCreatingBag() throws JsonProcessingException, JSONException {
        when(mockBagFactory.create(any(), any(), any())).thenReturn(mockBag);
        String newBagData = makeStringFromValidBagRequestJson();
        BagResponse bagResponse = bagService.create(newBagData, fileName);
        verify(mockBagRepository).save(mockBag);
        assertEquals(HttpStatus.OK, bagResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenUpdatingBag() {
        bagService.update(mockBag);
        verify(mockBagRepository).save(mockBag);
    }

    @Test
    void shouldPassWhenDeletingBag() {
        bagService.delete(mockBag);
        verify(mockBagRepository).deleteById(mockBag.getId());
    }

    @Test
    void shouldPassWhenFindingAllNotDeleted() {
        List<Bag> bags = createBagsForTesting();
        when(mockBagRepository.findAll()).thenReturn(bags);
        assertEquals(3, bags.size());
        bagService.findByDeleteStatus(false);
        assertEquals(2, bags.size());
    }

    @Test
    void shouldPassWhenFindingAllDeleted() {
        List<Bag> bags = createBagsForTesting();
        when(mockBagRepository.findAll()).thenReturn(bags);
        assertEquals(3, bags.size());
        bagService.findByDeleteStatus(true);
        assertEquals(1, bags.size());
    }

    private List<Bag> createBagsForTesting() {
        List<Bag> bags = new ArrayList<>();
        bags.add(makeTestBag(false));
        bags.add(makeTestBag(false));
        bags.add(makeTestBag(true));
        when(mockBagRepository.findAll()).thenReturn(bags);
        return bags;
    }

    private Bag makeTestBag(boolean deleteStatus) {
        Bag bag = mock(Bag.class);
        when(bag.isDeleted()).thenReturn(deleteStatus);
        return bag;
    }

    @Test
    void shouldPassWhenReadingJsonWithValidBagData() throws JsonProcessingException, JSONException {
        Bag bag = readBagFromValidJson();
        assertAll("bag",
                () -> assertEquals(new BigInteger("2"), bag.getId()),
                () -> assertEquals("Red Bag", bag.getName()),
                () -> assertEquals(new BigDecimal(20), bag.getPrice()),
                () -> assertEquals("SMALL", bag.getSize().getName()),
                () -> assertEquals("Dawn", bag.getMaker().getName()),
                () -> assertEquals("/images/Red Bag.png", bag.getImageUrl()),
                () -> assertFalse(bag.isDeleted())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectBagDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadBagFromJsonWithIncorrectDataType);
    }

    @Test
    void shouldPassWhenReadingJsonWithValidBagRequestData() throws JsonProcessingException, JSONException {
        BagRequest bagRequest = readBagRequestFromValidJson();
        assertAll("bagRequest",
                () -> assertEquals("Red Bag", bagRequest.getName()),
                () -> assertEquals(new BigDecimal(20), bagRequest.getPrice()),
                () -> assertEquals("SMALL", bagRequest.getSize().getName()),
                () -> assertEquals("Dawn", bagRequest.getMaker().getName())
        );
    }

    @Test
    void shouldThrowErrorWhenReadingJsonWithIncorrectBagRequestDataType() {
        assertThrows(JsonProcessingException.class, this::attemptToReadBagRequestFromJsonWithIncorrectDataType);
    }

    private Bag readBagFromValidJson() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Red Bag");
        json.put("type", makeTypeJson());
        json.put("price", 20);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Red Bag.png");
        json.put("deleted", "false");
        String data = json.toString();
        return bagService.readEntityData(data);
    }

    private void attemptToReadBagFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("id", 2);
        json.put("name", "Red Bag");
        json.put("type", makeTypeJson());
        json.put("price", "twenty");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        json.put("imageUrl", "/images/Red Bag.png");
        json.put("deleted", "false");
        String data = json.toString();
        bagService.readEntityData(data);
    }

    private BagRequest readBagRequestFromValidJson() throws JsonProcessingException, JSONException {
        String data = makeStringFromValidBagRequestJson();
        return bagService.readRequestData(data);
    }

    private String makeStringFromValidBagRequestJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Red Bag");
        json.put("price", 20);
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        return json.toString();
    }

    private void attemptToReadBagRequestFromJsonWithIncorrectDataType() throws JsonProcessingException, JSONException {
        JSONObject json = new JSONObject();
        json.put("name", "Red Bag");
        json.put("price", "twenty");    // Provide text rather than digits to force an error.
        json.put("size", makeSizeJson());
        json.put("maker", makeMakerJson());
        String data = json.toString();
        bagService.readRequestData(data);
    }

    private JSONObject makeTypeJson() throws JSONException {
        JSONObject type = new JSONObject();
        type.put("id", 1);
        type.put("name", "bags");
        type.put("imageUrl", "/images/Bag.png");
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