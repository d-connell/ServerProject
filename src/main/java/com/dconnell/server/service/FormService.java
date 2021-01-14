package com.dconnell.server.service;

import com.dconnell.server.response.formoptionsresponse.FormOptionsResponse;

public interface FormService {

    FormOptionsResponse findFormOptions(String type) throws NullPointerException;

}