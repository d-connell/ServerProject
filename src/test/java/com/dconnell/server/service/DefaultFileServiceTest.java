package com.dconnell.server.service;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefaultFileServiceTest {

    @Test
    void shouldPassWhenAttemptingToDeleteAFileWhichDoesNotExist() {
        assertDoesNotThrow(this::attemptToDeleteAFileWhichDoesNotExist);
    }

    private void attemptToDeleteAFileWhichDoesNotExist() throws IOException {
        DefaultFileService defaultFileService = new DefaultFileService();
        defaultFileService.delete("url");
    }

}