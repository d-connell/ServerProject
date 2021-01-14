package com.dconnell.server.model.inventory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private final String name = "name";
    private final String imageUrl = "imageUrl";
    private final Category category = new Category();

    @BeforeEach
    public void initialise() {
        category.setName(name);
        category.setImageUrl(imageUrl);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("type",
                () -> assertEquals(name, category.getName()),
                () -> assertEquals(imageUrl, category.getImageUrl())
        );
    }

}