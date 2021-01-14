package com.dconnell.server.controller.controllertools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminActionTest {

    private final String displayText = "Show in browser";
    private final String linkText = "Use in browser";
    private final AdminAction action = new AdminAction(displayText, linkText);

    @Test
    void shouldPassWhenGettingActionDetails() {
        assertAll("action",
                () -> assertEquals(displayText, action.getDisplayText()),
                () -> assertEquals(linkText, action.getLinkText()));
    }

}