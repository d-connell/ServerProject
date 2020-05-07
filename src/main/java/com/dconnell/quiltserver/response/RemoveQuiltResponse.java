package com.dconnell.quiltserver.response;

import org.springframework.http.HttpStatus;

public class RemoveQuiltResponse {

    private HttpStatus httpStatus;

    public RemoveQuiltResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}