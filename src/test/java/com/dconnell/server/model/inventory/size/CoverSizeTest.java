package com.dconnell.server.model.inventory.size;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CoverSizeTest {

    private final String name = "name";
    private final String dimensions = "dimensions";
    private final CoverSize coverSize = new CoverSize();

    @BeforeEach
    public void initialise() {
        coverSize.setName(name);
        coverSize.setDimensions(dimensions);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("coverSize",
                () -> assertEquals(name, coverSize.getName()),
                () -> assertEquals(dimensions, coverSize.getDimensions())
        );
    }

}