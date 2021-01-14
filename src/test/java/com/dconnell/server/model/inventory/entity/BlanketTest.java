package com.dconnell.server.model.inventory.entity;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.size.CoverSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BlanketTest {

    private final String name = "name";
    private final Type mockType = mock(Type.class);
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final CoverSize mockSize = mock(CoverSize.class);
    private final CoverSize newMockSize = mock(CoverSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final String imageUrl = "url";
    private final Blanket blanket = new Blanket();

    @BeforeEach
    public void initialise() {
        blanket.setName(name);
        blanket.setType(mockType);
        blanket.setPrice(price);
        blanket.setSize(mockSize);
        blanket.setMaker(mockMaker);
        blanket.setDeleted(false);
        blanket.setImageUrl(imageUrl);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("blanket",
                () -> assertEquals(name, blanket.getName()),
                () -> assertEquals(mockType, blanket.getType()),
                () -> assertEquals(price, blanket.getPrice()),
                () -> assertEquals(mockSize, blanket.getSize()),
                () -> assertEquals(mockMaker, blanket.getMaker()),
                () -> assertEquals(imageUrl, blanket.getImageUrl()),
                () -> assertFalse(blanket.isDeleted())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        blanket.setName(newName);
        assertEquals(newName, blanket.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        blanket.setPrice(newPrice);
        assertEquals(newPrice, blanket.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        blanket.setSize(newMockSize);
        assertEquals(newMockSize, blanket.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        blanket.setMaker(newMockMaker);
        assertEquals(newMockMaker, blanket.getMaker());
    }

    @Test
    void shouldPassWhenChangingImageUrl() {
        String newImageUrl = "newUrl";
        blanket.setImageUrl(newImageUrl);
        assertEquals(newImageUrl, blanket.getImageUrl());
    }

    @Test
    void shouldPassWhenChangingDeletedStatus() {
        blanket.setDeleted(true);
        assertTrue(blanket.isDeleted());
    }

}