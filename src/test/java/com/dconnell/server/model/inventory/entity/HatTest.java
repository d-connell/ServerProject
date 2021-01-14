package com.dconnell.server.model.inventory.entity;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.Type;
import com.dconnell.server.model.inventory.size.HatSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HatTest {

    private final String name = "name";
    private final Type mockType = mock(Type.class);
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final HatSize mockSize = mock(HatSize.class);
    private final HatSize newMockSize = mock(HatSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final String imageUrl = "url";
    private final Hat hat = new Hat();

    @BeforeEach
    public void initialise() {
        hat.setName(name);
        hat.setType(mockType);
        hat.setPrice(price);
        hat.setSize(mockSize);
        hat.setMaker(mockMaker);
        hat.setDeleted(false);
        hat.setImageUrl(imageUrl);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("hat",
                () -> assertEquals(name, hat.getName()),
                () -> assertEquals(mockType, hat.getType()),
                () -> assertEquals(price, hat.getPrice()),
                () -> assertEquals(mockSize, hat.getSize()),
                () -> assertEquals(mockMaker, hat.getMaker()),
                () -> assertEquals(imageUrl, hat.getImageUrl()),
                () -> assertFalse(hat.isDeleted())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        hat.setName(newName);
        assertEquals(newName, hat.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        hat.setPrice(newPrice);
        assertEquals(newPrice, hat.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        hat.setSize(newMockSize);
        assertEquals(newMockSize, hat.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        hat.setMaker(newMockMaker);
        assertEquals(newMockMaker, hat.getMaker());
    }

    @Test
    void shouldPassWhenChangingImageUrl() {
        String newImageUrl = "newUrl";
        hat.setImageUrl(newImageUrl);
        assertEquals(newImageUrl, hat.getImageUrl());
    }

    @Test
    void shouldPassWhenChangingDeletedStatus() {
        hat.setDeleted(true);
        assertTrue(hat.isDeleted());
    }

}