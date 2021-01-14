package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Hat;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HatResponseTest {

    private final Hat mockHat = mock(Hat.class);
    private final HttpStatus ok = HttpStatus.OK;
    private final HatResponse hatResponse = new HatResponse(mockHat, ok);

    @Test
    void shouldPassWhenGettingHttpStatus() {
        assertEquals(ok, hatResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenGettingBag() {
        assertEquals(mockHat, hatResponse.getHat());
    }

}