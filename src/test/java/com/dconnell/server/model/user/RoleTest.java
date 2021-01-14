package com.dconnell.server.model.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private final String name = "name";
    private final Role role = new Role();

    @BeforeEach
    public void initialise() {
        role.setName(name);
    }

    @Test
    void shouldPassWhenGettingInitialDetails() {
        assertEquals(name, role.getName());
    }

}