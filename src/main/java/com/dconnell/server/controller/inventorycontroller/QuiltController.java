package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.Quilt;
import com.dconnell.server.response.inventoryresponse.QuiltResponse;
import com.dconnell.server.service.DefaultFileService;
import com.dconnell.server.service.inventoryservice.QuiltService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;

@RestController
@RequestMapping("/quilts")
public class QuiltController implements InventoryController<Quilt> {

    private final QuiltService quiltService;
    private final DefaultFileService fileService;

    public QuiltController(QuiltService quiltService,
                           DefaultFileService fileService) {
        this.quiltService = quiltService;
        this.fileService = fileService;
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @GetMapping(path = "/remove")
    public Iterable<Quilt> getAllNotDeleted() {
        return quiltService.findByDeleteStatus(false);
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger create(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image") MultipartFile image) throws IOException {
        String fileName = fileService.generateRandomFileName();
        fileService.save(image, fileName);
        QuiltResponse quiltResponse = quiltService.create(data, fileName);
        return quiltResponse.getQuilt().getId();
    }

    @PreAuthorize("hasAuthority('MAKER')")
    @Override
    @PutMapping(path = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BigInteger update(@RequestParam(value = "data") String data,
                             @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Quilt quilt = quiltService.readEntityData(data);
        if (image != null) {
            changeImageFile(fileService, quilt, image);
        }
        quiltService.update(quilt);
        return quilt.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @PutMapping(path = "/restore")
    public HttpStatus restore(String data) throws IOException {
        Quilt quilt = quiltService.readEntityData(data);
        return quiltService.update(quilt);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @DeleteMapping(path = "/delete")
    public HttpStatus delete(@RequestBody Quilt quilt) throws IOException {
        fileService.delete(quilt.getImageUrl());
        return quiltService.delete(quilt);
    }

}