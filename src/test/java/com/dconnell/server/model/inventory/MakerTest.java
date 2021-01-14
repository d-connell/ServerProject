package com.dconnell.server.model.inventory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MakerTest {

    private final String name = "name";
    private final Maker maker = new Maker();

    @BeforeEach
    public void initialise() {
        maker.setName(name);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertEquals(name, maker.getName());
    }

}