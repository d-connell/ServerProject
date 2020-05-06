package com.dconnell.quiltserver.controller;

import com.dconnell.quiltserver.response.NewQuiltResponse;
import com.dconnell.quiltserver.respository.QuiltRepository;
import com.dconnell.quiltserver.service.QuiltCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
public class QuiltController {

    @Autowired
    private QuiltRepository quiltRepository;

    @Autowired
    private QuiltCreationService quiltCreationService;

    private static final Logger logger = LoggerFactory.getLogger(QuiltController.class);

    @GetMapping(path = "/quilts")
    public ResponseEntity<?> getAllQuilts() {
        String response = "Available quilts: "
                + quiltRepository.findAll().stream().map(quilt -> quilt.getName())
                .collect(Collectors.joining(", "));
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<NewQuiltResponse> addNewQuilt(@RequestParam String name,
                                                        @RequestParam String size, @RequestParam BigDecimal price) {
        if (quiltRepository.existsByName(name)) {
            logger.info("A quilt with this name already exists.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        NewQuiltResponse newQuiltResponse = quiltCreationService.create(name, size, price);
        return new ResponseEntity(newQuiltResponse.getHttpStatus());
    }

}
