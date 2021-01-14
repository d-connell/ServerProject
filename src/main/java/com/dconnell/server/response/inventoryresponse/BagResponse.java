package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Bag;
import org.springframework.http.HttpStatus;

public class BagResponse extends InventoryResponse {

    private final Bag bag;
    private final HttpStatus httpStatus;

    public BagResponse(Bag bag, HttpStatus httpStatus) {
        this.bag = bag;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Bag getBag() {
        return bag;
    }

}