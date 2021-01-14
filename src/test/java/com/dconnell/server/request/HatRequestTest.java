package com.dconnell.server.request;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.HatSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HatRequestTest {

    private final String name = "name";
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final HatSize mockSize = mock(HatSize.class);
    private final HatSize newMockSize = mock(HatSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final HatRequest hatRequest = new HatRequest();

    @BeforeEach
    public void initialise() {
        hatRequest.setName(name);
        hatRequest.setPrice(price);
        hatRequest.setSize(mockSize);
        hatRequest.setMaker(mockMaker);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("hatRequest",
                () -> assertEquals(name, hatRequest.getName()),
                () -> assertEquals(price, hatRequest.getPrice()),
                () -> assertEquals(mockSize, hatRequest.getSize()),
                () -> assertEquals(mockMaker, hatRequest.getMaker())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        hatRequest.setName(newName);
        assertEquals(newName, hatRequest.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        hatRequest.setPrice(newPrice);
        assertEquals(newPrice, hatRequest.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        hatRequest.setSize(newMockSize);
        assertEquals(newMockSize, hatRequest.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        hatRequest.setMaker(newMockMaker);
        assertEquals(newMockMaker, hatRequest.getMaker());
    }

}