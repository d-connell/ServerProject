package com.wackywallaby.bareboneserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping(path="/hello")
    public ResponseEntity<?> helloWorld() {
        logger.info("Hello World!");
        return new ResponseEntity("RESPONSE", HttpStatus.OK);
    }

    @PostMapping(path="/user")
    public ResponseEntity<?> newUser(@RequestParam String name) {
        String response = "Hello, " + name + "!";
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.OK);
    }

    @PutMapping(path="/user")
    public ResponseEntity<?> greetUser(@RequestParam String name) {
        String response = "Greetings, " + name + "!";
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.ACCEPTED);
    }

    @PatchMapping(path="/user")
    public ResponseEntity<?> welcomeUser(@RequestParam String name) {
        String response = "Welcome, " + name + "!";
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/user")
    public ResponseEntity<?> deleteUser(@RequestParam String name) {
        String response = "User " + name + " has been deleted.";
        logger.info(response);
        return new ResponseEntity("RESPONSE", HttpStatus.ACCEPTED);
    }

}
