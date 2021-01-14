package com.dconnell.server.response.inventoryresponse;

import com.dconnell.server.model.inventory.entity.Hat;
import org.springframework.http.HttpStatus;

public class HatResponse extends InventoryResponse {

    private final Hat hat;
    private final HttpStatus httpStatus;

    public HatResponse(Hat hat, HttpStatus httpStatus) {
        this.hat = hat;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Hat getHat() {
        return hat;
    }

}