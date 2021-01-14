package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Bag;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BagResponseTest {

    private final Bag mockBag = mock(Bag.class);
    private final HttpStatus ok = HttpStatus.OK;
    private final BagResponse bagResponse = new BagResponse(mockBag, ok);

    @Test
    void shouldPassWhenGettingHttpStatus() {
        assertEquals(ok, bagResponse.getHttpStatus());
    }

    @Test
    void shouldPassWhenGettingBag() {
        assertEquals(mockBag, bagResponse.getBag());
    }

}