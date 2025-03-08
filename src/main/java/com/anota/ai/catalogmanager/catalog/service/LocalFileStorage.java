package com.anota.ai.catalogmanager.catalog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class LocalFileStorage implements FileStorage{

    @Value("${catalog.generate.directory}")
    private String directoryName;


    @Override
    public void saveTextFile(String text, String filename) throws IOException {
        Path directoryPath = Path.of(directoryName);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        Path filePath = directoryPath.resolve(filename);
        Files.writeString(filePath, text, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public String readTextFile(String filename) throws IOException {
        Path directoryPath = Path.of(directoryName);
        Path filePath = directoryPath.resolve(filename);
        return Files.readString(filePath);
    }
}
