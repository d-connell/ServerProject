package com.dconnell.server.controller;

import com.dconnell.server.response.formoptionsresponse.FormOptionsResponse;
import com.dconnell.server.service.FormService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping(path = "/")
    public FormOptionsResponse getFormOptions(@RequestParam String type) {
        return formService.findFormOptions(type);
    }

}