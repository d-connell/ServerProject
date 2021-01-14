package com.dconnell.server.model.inventory.size;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class HatSizeTest {

    private final String name = "name";
    private final String dimensions = "dimensions";
    private final HatSize hatSize = new HatSize();

    @BeforeEach
    public void initialise() {
        hatSize.setName(name);
        hatSize.setDimensions(dimensions);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("hatSize",
                () -> assertEquals(name, hatSize.getName()),
                () -> assertEquals(dimensions, hatSize.getDimensions())
        );
    }

}