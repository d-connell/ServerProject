package com.dconnell.server.request;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.CoverSize;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuiltRequestTest {

    private final String name = "name";
    private final BigDecimal price = new BigDecimal("1");
    private final BigDecimal newPrice = new BigDecimal("2");
    private final CoverSize mockSize = mock(CoverSize.class);
    private final CoverSize newMockSize = mock(CoverSize.class);
    private final Maker mockMaker = mock(Maker.class);
    private final Maker newMockMaker = mock(Maker.class);
    private final QuiltRequest quiltRequest = new QuiltRequest();

    @BeforeEach
    public void initialise() {
        quiltRequest.setName(name);
        quiltRequest.setPrice(price);
        quiltRequest.setSize(mockSize);
        quiltRequest.setMaker(mockMaker);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("quiltRequest",
                () -> assertEquals(name, quiltRequest.getName()),
                () -> assertEquals(price, quiltRequest.getPrice()),
                () -> assertEquals(mockSize, quiltRequest.getSize()),
                () -> assertEquals(mockMaker, quiltRequest.getMaker())
        );
    }

    @Test
    void shouldPassWhenChangingName() {
        String newName = "newName";
        quiltRequest.setName(newName);
        assertEquals(newName, quiltRequest.getName());
    }

    @Test
    void shouldPassWhenChangingPrice() {
        quiltRequest.setPrice(newPrice);
        assertEquals(newPrice, quiltRequest.getPrice());
    }

    @Test
    void shouldPassWhenChangingSize() {
        quiltRequest.setSize(newMockSize);
        assertEquals(newMockSize, quiltRequest.getSize());
    }

    @Test
    void shouldPassWhenChangingMaker() {
        quiltRequest.setMaker(newMockMaker);
        assertEquals(newMockMaker, quiltRequest.getMaker());
    }

}