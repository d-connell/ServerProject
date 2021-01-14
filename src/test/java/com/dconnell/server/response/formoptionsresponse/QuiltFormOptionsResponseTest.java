package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.CoverSize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class QuiltFormOptionsResponseTest {

    private final Iterable<CoverSize> sizes = mock(Iterable.class);
    private final Iterable<Maker> makers = mock(Iterable.class);
    private final Iterable<CoverSize> newSizes = mock(Iterable.class);
    private final Iterable<Maker> newMakers = mock(Iterable.class);
    private QuiltFormOptionsResponse quiltFormOptionsResponse;

    @BeforeEach
    void initialise() {
        quiltFormOptionsResponse = new QuiltFormOptionsResponse(sizes, makers);
    }

    @Test
    void shouldPassWhenGettingSizes() {
        assertEquals(sizes, quiltFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenGettingMakers() {
        assertEquals(makers, quiltFormOptionsResponse.getMakers());
    }

    @Test
    void shouldPassWhenSettingSizes() {
        quiltFormOptionsResponse.setSizes(newSizes);
        assertEquals(newSizes, quiltFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenSettingMakers() {
        quiltFormOptionsResponse.setMakers(newMakers);
        assertEquals(newMakers, quiltFormOptionsResponse.getMakers());
    }

}