package com.dconnell.server.model.inventory.size;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class BagSizeTest {

    private final String name = "name";
    private final String dimensions = "dimensions";
    private final BagSize bagSize = new BagSize();

    @BeforeEach
    public void initialise() {
        bagSize.setName(name);
        bagSize.setDimensions(dimensions);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("bagSize",
                () -> assertEquals(name, bagSize.getName()),
                () -> assertEquals(dimensions, bagSize.getDimensions())
        );
    }

}