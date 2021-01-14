package com.dconnell.server.request;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.CoverSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlanketRequestTest {

    private final String name = "name";
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final CoverSize mockSize = mock(CoverSize.class);
    private final CoverSize newMockSize = mock(CoverSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final BlanketRequest blanketRequest = new BlanketRequest();

    @BeforeEach
    public void initialise() {
        blanketRequest.setName(name);
        blanketRequest.setPrice(price);
        blanketRequest.setSize(mockSize);
        blanketRequest.setMaker(mockMaker);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("blanketRequest",
                () -> assertEquals(name, blanketRequest.getName()),
                () -> assertEquals(price, blanketRequest.getPrice()),
                () -> assertEquals(mockSize, blanketRequest.getSize()),
                () -> assertEquals(mockMaker, blanketRequest.getMaker())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        blanketRequest.setName(newName);
        assertEquals(newName, blanketRequest.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        blanketRequest.setPrice(newPrice);
        assertEquals(newPrice, blanketRequest.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        blanketRequest.setSize(newMockSize);
        assertEquals(newMockSize, blanketRequest.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        blanketRequest.setMaker(newMockMaker);
        assertEquals(newMockMaker, blanketRequest.getMaker());
    }

}