package com.dconnell.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void delete(String url) throws IOException;

    void save(MultipartFile file, String url) throws IOException;

    String generateRandomFileName();

}