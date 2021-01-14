package com.dconnell.server.model.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeTest {

    @Test
    void shouldPassToConfirmTypesHaveRightLabels() {
        assertAll("labels",
                () -> assertEquals("bags", Type.BAGS.getLabel()),
                () -> assertEquals("blankets", Type.BLANKETS.getLabel()),
                () -> assertEquals("hats", Type.HATS.getLabel()),
                () -> assertEquals("quilts", Type.QUILTS.getLabel())
        );
    }

    @Test
    void shouldPassToConfirmCorrectTypeFoundForBags() {
        Type type = Type.findType(Type.BAGS.getLabel());
        assertEquals(Type.BAGS, type);
    }

    @Test
    void shouldPassToConfirmCorrectTypeFoundForBlankets() {
        Type type = Type.findType(Type.BLANKETS.getLabel());
        assertEquals(Type.BLANKETS, type);
    }

    @Test
    void shouldPassToConfirmCorrectTypeFoundForHats() {
        Type type = Type.findType(Type.HATS.getLabel());
        assertEquals(Type.HATS, type);
    }

    @Test
    void shouldPassToConfirmCorrectTypeFoundForQuilts() {
        Type type = Type.findType(Type.QUILTS.getLabel());
        assertEquals(Type.QUILTS, type);
    }

    @Test
    void shouldPassToConfirmNullTypeFoundForUnknownType() {
        Type type = Type.findType("unknown");
        assertNull(type);
    }

}