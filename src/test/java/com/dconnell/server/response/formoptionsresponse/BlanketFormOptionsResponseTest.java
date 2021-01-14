package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.CoverSize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BlanketFormOptionsResponseTest {

    private final Iterable<CoverSize> sizes = mock(Iterable.class);
    private final Iterable<Maker> makers = mock(Iterable.class);
    private final Iterable<CoverSize> newSizes = mock(Iterable.class);
    private final Iterable<Maker> newMakers = mock(Iterable.class);
    private BlanketFormOptionsResponse blanketFormOptionsResponse;

    @BeforeEach
    void initialise() {
        blanketFormOptionsResponse = new BlanketFormOptionsResponse(sizes, makers);
    }

    @Test
    void shouldPassWhenGettingSizes() {
        assertEquals(sizes, blanketFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenGettingMakers() {
        assertEquals(makers, blanketFormOptionsResponse.getMakers());
    }

    @Test
    void shouldPassWhenSettingSizes() {
        blanketFormOptionsResponse.setSizes(newSizes);
        assertEquals(newSizes, blanketFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenSettingMakers() {
        blanketFormOptionsResponse.setMakers(newMakers);
        assertEquals(newMakers, blanketFormOptionsResponse.getMakers());
    }

}