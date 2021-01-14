package com.dconnell.server.model.inventory.entity;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.size.CoverSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuiltTest {

    private final String name = "name";
    private final Type mockType = mock(Type.class);
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final CoverSize mockSize = mock(CoverSize.class);
    private final CoverSize newMockSize = mock(CoverSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final String imageUrl = "url";
    private final Quilt quilt = new Quilt();

    @BeforeEach
    public void initialise() {
        quilt.setName(name);
        quilt.setType(mockType);
        quilt.setPrice(price);
        quilt.setSize(mockSize);
        quilt.setMaker(mockMaker);
        quilt.setDeleted(false);
        quilt.setImageUrl(imageUrl);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("quilt",
                () -> assertEquals(name, quilt.getName()),
                () -> assertEquals(mockType, quilt.getType()),
                () -> assertEquals(price, quilt.getPrice()),
                () -> assertEquals(mockSize, quilt.getSize()),
                () -> assertEquals(mockMaker, quilt.getMaker()),
                () -> assertEquals(imageUrl, quilt.getImageUrl()),
                () -> assertFalse(quilt.isDeleted())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        quilt.setName(newName);
        assertEquals(newName, quilt.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        quilt.setPrice(newPrice);
        assertEquals(newPrice, quilt.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        quilt.setSize(newMockSize);
        assertEquals(newMockSize, quilt.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        quilt.setMaker(newMockMaker);
        assertEquals(newMockMaker, quilt.getMaker());
    }

    @Test
    void shouldPassWhenChangingImageUrl() {
        String newImageUrl = "newUrl";
        quilt.setImageUrl(newImageUrl);
        assertEquals(newImageUrl, quilt.getImageUrl());
    }

    @Test
    void shouldPassWhenChangingDeletedStatus() {
        quilt.setDeleted(true);
        assertTrue(quilt.isDeleted());
    }

}