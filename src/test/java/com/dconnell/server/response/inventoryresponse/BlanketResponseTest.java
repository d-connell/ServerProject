package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Blanket;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlanketResponseTest {

    private final Blanket mockBlanket = mock(Blanket.class);
    private final HttpStatus ok = HttpStatus.OK;
    private final BlanketResponse blanketResponse = new BlanketResponse(mockBlanket, ok);

    @Test
    void shouldPassWhenGettingHttpStatus() {
        assertEquals(ok, blanketResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenGettingBag() {
        assertEquals(mockBlanket, blanketResponse.getBlanket());
    }

}