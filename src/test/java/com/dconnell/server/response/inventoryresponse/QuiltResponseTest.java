package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Quilt;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuiltResponseTest {

    private final Quilt mockQuilt = mock(Quilt.class);
    private final HttpStatus ok = HttpStatus.OK;
    private final QuiltResponse quiltResponse = new QuiltResponse(mockQuilt, ok);

    @Test
    void shouldPassWhenGettingHttpStatus() {
        assertEquals(ok, quiltResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenGettingBag() {
        assertEquals(mockQuilt, quiltResponse.getQuilt());
    }

}