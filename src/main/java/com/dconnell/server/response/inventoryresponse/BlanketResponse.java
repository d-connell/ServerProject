package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Blanket;
import org.springframework.http.HttpStatus;

public class BlanketResponse extends InventoryResponse {

    private final Blanket blanket;
    private final HttpStatus httpStatus;

    public BlanketResponse(Blanket blanket, HttpStatus httpStatus) {
        this.blanket = blanket;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Blanket getBlanket() {
        return blanket;
    }

}