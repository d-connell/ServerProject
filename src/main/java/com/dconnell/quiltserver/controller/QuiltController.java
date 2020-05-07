package com.dconnell.quiltserver.controller;

import com.dconnell.quiltserver.model.Quilt;
import com.dconnell.quiltserver.response.NewQuiltResponse;
import com.dconnell.quiltserver.response.RemoveQuiltResponse;
import com.dconnell.quiltserver.respository.QuiltRepository;
import com.dconnell.quiltserver.service.InventoryAmendmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/quilts")
public class QuiltController {

    @Autowired
    private QuiltRepository quiltRepository;

    @Autowired
    private InventoryAmendmentService inventoryAmendmentService;

    @GetMapping(path = "/names")
    public Set<String> allQuiltNames() {
        return quiltRepository.findAll().stream().map(Quilt::getName).collect(toSet());
    }

    @GetMapping()
    public List<Quilt> allQuilts() {
        return quiltRepository.findAll();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> addNewQuilt(@RequestParam String name,
                                                        @RequestParam String size, @RequestParam BigDecimal price) {
        if (quiltRepository.existsByName(name)) {
            return new ResponseEntity<>("This name is already in use.", HttpStatus.BAD_REQUEST);
        }
        NewQuiltResponse newQuiltResponse = inventoryAmendmentService.create(name, size, price);
        return new ResponseEntity(newQuiltResponse.getHttpStatus());
    }

    @DeleteMapping(path = "/remove")
    public ResponseEntity<String> removeQuilt(@RequestParam String name) {
        if (!quiltRepository.existsByName(name)) {
            String response = "Quilt " + name + " is not present to be removed.";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        RemoveQuiltResponse removeQuiltResponse = inventoryAmendmentService.remove(name);
        return new ResponseEntity<>("Quilt removed", removeQuiltResponse.getHttpStatus());
    }

}