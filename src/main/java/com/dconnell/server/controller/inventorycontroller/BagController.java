package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Bag;
import com.dconnell.server.response.inventoryresponse.BagResponse;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.BagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;

@RestController
@RequestMapping("/bags")
public class BagController implements InventoryController<Bag> {

    private final BagService bagService;
    private final DefaultFileService fileService;

    public BagController(BagService bagService,
                         DefaultFileService fileService) {
        this.bagService = bagService;
        this.fileService = fileService;
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @GetMapping(path = "/remove")
    public Iterable<Bag> getAllNotDeleted() {
        return bagService.findByDeleteStatus(false);
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger create(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image") MultipartFile image) throws IOException {
        String imageFileName = fileService.generateRandomFileName();
        fileService.save(image, imageFileName);
        BagResponse bagResponse = bagService.create(data, imageFileName);
        return bagResponse.getBag().getId();
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PutMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger update(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Bag bag = bagService.readEntityData(data);
        if (image != null) {
            changeImageFile(fileService, bag, image);
        }
        bagService.update(bag);
        return bag.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @PutMapping(path = "/restore")
    public HttpStatus restore(@RequestParam(value = "data") String data) throws IOException {
        Bag bag = bagService.readEntityData(data);
        return bagService.update(bag);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @DeleteMapping(path = "/delete")
    public HttpStatus delete(Bag bag) throws IOException {
        fileService.delete(bag.getImageUrl());
        return bagService.delete(bag);
    }

}