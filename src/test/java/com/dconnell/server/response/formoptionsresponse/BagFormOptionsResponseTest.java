package com.dconnell.server.response.formoptionsresponse;

import com.dconnell.server.model.inventory.Maker;
import com.dconnell.server.model.inventory.size.BagSize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BagFormOptionsResponseTest {

    private final Iterable<BagSize> sizes = mock(Iterable.class);
    private final Iterable<Maker> makers = mock(Iterable.class);
    private final Iterable<BagSize> newSizes = mock(Iterable.class);
    private final Iterable<Maker> newMakers = mock(Iterable.class);
    private BagFormOptionsResponse bagFormOptionsResponse;

    @BeforeEach
    void initialise() {
        bagFormOptionsResponse = new BagFormOptionsResponse(sizes, makers);
    }

    @Test
    void shouldPassWhenGettingSizes() {
        assertEquals(sizes, bagFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenGettingMakers() {
        assertEquals(makers, bagFormOptionsResponse.getMakers());
    }

    @Test
    void shouldPassWhenSettingSizes() {
        bagFormOptionsResponse.setSizes(newSizes);
        assertEquals(newSizes, bagFormOptionsResponse.getSizes());
    }

    @Test
    void shouldPassWhenSettingMakers() {
        bagFormOptionsResponse.setMakers(newMakers);
        assertEquals(newMakers, bagFormOptionsResponse.getMakers());
    }

}