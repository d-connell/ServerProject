package com.dconnell.server.model.inventory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TypeTest {

    private final String name = "name";
    private final String imageUrl = "imageUrl";
    private final Category category = mock(Category.class);
    private final Type type = new Type();

    @BeforeEach
    public void initialise() {
        type.setName(name);
        type.setImageUrl(imageUrl);
        type.setCategory(category);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertAll("type",
                () -> assertEquals(name, type.getName()),
                () -> assertEquals(imageUrl, type.getImageUrl()),
                () -> assertEquals(category, type.getCategory())
        );
    }

}