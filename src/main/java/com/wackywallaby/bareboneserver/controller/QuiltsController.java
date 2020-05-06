package com.wackywallaby.bareboneserver.controller;

import com.wackywallaby.bareboneserver.model.Quilt;
import com.wackywallaby.bareboneserver.respository.QuiltRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuiltsController {

    @Autowired
    private QuiltRepository quiltRepository;

    private static final Logger logger = LoggerFactory.getLogger(QuiltsController.class);

    @GetMapping(path = "/quilts")
    public ResponseEntity<?> getAllQuilts() {
        List<Quilt> allQuilts = quiltRepository.findAll();
        String response = "Available quilts: "
                + allQuilts.stream()
                .map(quilt -> quilt.getName())
                .collect(Collectors.joining(", "));
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.OK);
    }

    @PostMapping(path = "/quilts")
    public ResponseEntity<Quilt> addNewQuilt(@RequestParam String name,
                                             @RequestParam String size, @RequestParam BigDecimal price) {
        String response = "A new quilt has been added: " + name;
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.ACCEPTED);
    }

    @PatchMapping(path = "/quilts")
    public ResponseEntity<Quilt> replaceQuilt(@RequestParam String name, @RequestParam BigDecimal price) {
        String response = "Quilt " + name + " now has price $" + price;
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/quilts")
    public ResponseEntity<Quilt> removeQuilt(@RequestParam String name) {
        String response = "Quilt " + name + " has been removed.";
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.ACCEPTED);
    }

}
