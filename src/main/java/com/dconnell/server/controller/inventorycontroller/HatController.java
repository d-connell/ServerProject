package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Hat;
import com.dconnell.server.response.inventoryresponse.HatResponse;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.HatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;

@RestController
@RequestMapping("/hats")
public class HatController implements InventoryController<Hat> {

    private final HatService hatService;
    private final DefaultFileService fileService;

    public HatController(HatService hatService, DefaultFileService fileService) {
        this.hatService = hatService;
        this.fileService = fileService;
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @GetMapping(path = "/remove")
    public Iterable<Hat> getAllNotDeleted() {
        return hatService.findByDeleteStatus(false);
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger create(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image") MultipartFile image) throws IOException {
        String imageFileName = fileService.generateRandomFileName();
        fileService.save(image, imageFileName);
        HatResponse hatResponse = hatService.create(data, imageFileName);
        return hatResponse.getHat().getId();
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PutMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger update(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Hat hat = hatService.readEntityData(data);
        if (image != null) {
            changeImageFile(fileService, hat, image);
        }
        hatService.update(hat);
        return hat.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @PutMapping(path = "/restore")
    public HttpStatus restore(@RequestParam(value = "data") String data) throws IOException {
        Hat hat = hatService.readEntityData(data);
        return hatService.update(hat);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @DeleteMapping(path = "/delete")
    public HttpStatus delete(@RequestBody Hat hat) throws IOException {
        fileService.delete(hat.getImageUrl());
        return hatService.delete(hat);
    }

}