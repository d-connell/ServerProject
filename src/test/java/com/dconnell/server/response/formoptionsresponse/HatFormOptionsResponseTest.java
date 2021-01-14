package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.HatSize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HatFormOptionsResponseTest {

    private final Iterable<HatSize> sizes = mock(Iterable.class);
    private final Iterable<Maker> makers = mock(Iterable.class);
    private final Iterable<HatSize> newSizes = mock(Iterable.class);
    private final Iterable<Maker> newMakers = mock(Iterable.class);
    private HatFormOptionsResponse hatFormOptionsResponse;

    @BeforeEach
    void initialise() {
        hatFormOptionsResponse = new HatFormOptionsResponse(sizes, makers);
    }

    @Test
    void shouldPassWhenGettingSizes() {
        assertEquals(sizes, hatFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenGettingMakers() {
        assertEquals(makers, hatFormOptionsResponse.getMakers());
    }

    @Test
    void shouldPassWhenSettingSizes() {
        hatFormOptionsResponse.setSizes(newSizes);
        assertEquals(newSizes, hatFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenSettingMakers() {
        hatFormOptionsResponse.setMakers(newMakers);
        assertEquals(newMakers, hatFormOptionsResponse.getMakers());
    }

}