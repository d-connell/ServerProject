package com.dconnell.server.request;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.BagSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BagRequestTest {

    private final String name = "name";
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final BagSize mockSize = mock(BagSize.class);
    private final BagSize newMockSize = mock(BagSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final BagRequest bagRequest = new BagRequest();

    @BeforeEach
    public void initialise() {
        bagRequest.setName(name);
        bagRequest.setPrice(price);
        bagRequest.setSize(mockSize);
        bagRequest.setMaker(mockMaker);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("bagRequest",
                () -> assertEquals(name, bagRequest.getName()),
                () -> assertEquals(price, bagRequest.getPrice()),
                () -> assertEquals(mockSize, bagRequest.getSize()),
                () -> assertEquals(mockMaker, bagRequest.getMaker())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        bagRequest.setName(newName);
        assertEquals(newName, bagRequest.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        bagRequest.setPrice(newPrice);
        assertEquals(newPrice, bagRequest.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        bagRequest.setSize(newMockSize);
        assertEquals(newMockSize, bagRequest.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        bagRequest.setMaker(newMockMaker);
        assertEquals(newMockMaker, bagRequest.getMaker());
    }

}