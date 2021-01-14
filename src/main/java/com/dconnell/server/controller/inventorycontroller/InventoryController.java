package com.dconnell.server.controller.inventorycontroller;

import com.dconnell.server.model.inventory.entity.InventoryEntity;
import com.dconnell.server.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;

public interface InventoryController<TYP extends InventoryEntity> {

    Iterable<TYP> getAllNotDeleted();

    BigInteger create(@RequestParam(value = "data") String data,
                      @RequestParam(value = "image") MultipartFile image) throws IOException;

    BigInteger update(@RequestParam(value = "data") String data,
                      @RequestParam(value = "image", required = false) MultipartFile image) throws IOException;

    HttpStatus restore(@RequestParam(value = "data") String data) throws IOException;

    HttpStatus delete(@RequestBody TYP inventoryEntity) throws IOException;

    default void changeImageFile(FileService fileService, TYP item, MultipartFile image) throws IOException {
        fileService.delete(item.getImageUrl());
        String fileName = fileService.generateRandomFileName();
        fileService.save(image, fileName);
        item.setImageUrl("/uploads/" + fileName);
    }

}