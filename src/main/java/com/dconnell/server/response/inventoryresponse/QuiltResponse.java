package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Quilt;
import org.springframework.http.HttpStatus;

public class QuiltResponse extends InventoryResponse {

    private final Quilt quilt;
    private final HttpStatus httpStatus;

    public QuiltResponse(Quilt quilt, HttpStatus httpStatus) {
        this.quilt = quilt;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Quilt getQuilt() {
        return quilt;
    }

}