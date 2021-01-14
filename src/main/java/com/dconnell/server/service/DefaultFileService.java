package com.dconnell.server.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class DefaultFileService implements FileService {

    @Override
    public void delete(String url) throws IOException {
        Path path = Paths.get(System.getProperty(("user.dir")) + url);
        Files.deleteIfExists(path);
    }

    @Override
    public void save(MultipartFile file, String url) throws IOException {
        InputStream fileInputStream = file.getInputStream();
        File fileToSave = new File(System.getProperty(("user.dir")) + "/uploads/" + url);
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public String generateRandomFileName() {
        return UUID.randomUUID().toString();
    }

}