package com.dconnell.server.model.inventory.entity;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.size.BagSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BagTest {

    private final String name = "name";
    private final Type mockType = mock(Type.class);
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final BagSize mockSize = mock(BagSize.class);
    private final BagSize newMockSize = mock(BagSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final String imageUrl = "url";
    private final Bag bag = new Bag();

    @BeforeEach
    public void initialise() {
        bag.setName(name);
        bag.setType(mockType);
        bag.setPrice(price);
        bag.setSize(mockSize);
        bag.setMaker(mockMaker);
        bag.setDeleted(false);
        bag.setImageUrl(imageUrl);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("bag",
                () -> assertEquals(name, bag.getName()),
                () -> assertEquals(mockType, bag.getType()),
                () -> assertEquals(price, bag.getPrice()),
                () -> assertEquals(mockSize, bag.getSize()),
                () -> assertEquals(mockMaker, bag.getMaker()),
                () -> assertEquals(imageUrl, bag.getImageUrl()),
                () -> assertFalse(bag.isDeleted())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        bag.setName(newName);
        assertEquals(newName, bag.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        bag.setPrice(newPrice);
        assertEquals(newPrice, bag.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        bag.setSize(newMockSize);
        assertEquals(newMockSize, bag.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        bag.setMaker(newMockMaker);
        assertEquals(newMockMaker, bag.getMaker());
    }

    @Test
    void shouldPassWhenChangingImageUrl() {
        String newImageUrl = "newUrl";
        bag.setImageUrl(newImageUrl);
        assertEquals(newImageUrl, bag.getImageUrl());
    }

    @Test
    void shouldPassWhenChangingDeletedStatus() {
        bag.setDeleted(true);
        assertTrue(bag.isDeleted());
    }

}