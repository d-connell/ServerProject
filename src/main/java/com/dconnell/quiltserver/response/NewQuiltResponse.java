package com.dconnell.quiltserver.response;

import com.dconnell.quiltserver.model.Quilt;
import org.springframework.http.HttpStatus;

public class NewQuiltResponse {

    private Quilt quilt;
    private HttpStatus httpStatus;

    public NewQuiltResponse(Quilt quilt, HttpStatus httpStatus) {
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