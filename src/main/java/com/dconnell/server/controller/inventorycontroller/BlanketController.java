package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Blanket;
import com.dconnell.server.response.inventoryresponse.BlanketResponse;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.BlanketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;

@RestController
@RequestMapping("/blankets")
public class BlanketController implements InventoryController<Blanket> {

    private final BlanketService blanketService;
    private final DefaultFileService fileService;

    public BlanketController(BlanketService blanketService,
                             DefaultFileService fileService) {
        this.blanketService = blanketService;
        this.fileService = fileService;
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @GetMapping(path = "/remove")
    public Iterable<Blanket> getAllNotDeleted() {
        return blanketService.findByDeleteStatus(false);
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger create(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image") MultipartFile image) throws IOException {
        String imageFileName = fileService.generateRandomFileName();
        fileService.save(image, imageFileName);
        BlanketResponse blanketResponse = blanketService.create(data, imageFileName);
        return blanketResponse.getBlanket().getId();
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PutMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger update(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Blanket blanket = blanketService.readEntityData(data);
        if (image != null) {
            changeImageFile(fileService, blanket, image);
        }
        blanketService.update(blanket);
        return blanket.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @PutMapping(path = "/restore")
    public HttpStatus restore(@RequestParam(value = "data") String data) throws IOException {
        Blanket blanket = blanketService.readEntityData(data);
        return blanketService.update(blanket);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @DeleteMapping(path = "/delete")
    public HttpStatus delete(@RequestBody Blanket blanket) throws IOException {
        fileService.delete(blanket.getImageUrl());
        return blanketService.delete(blanket);
    }

}