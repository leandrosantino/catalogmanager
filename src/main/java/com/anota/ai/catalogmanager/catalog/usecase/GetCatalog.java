package com.anota.ai.catalogmanager.catalog.usecase;

import com.anota.ai.catalogmanager.catalog.CatalogModel;
import com.anota.ai.catalogmanager.catalog.service.FileStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class GetCatalog {

    @Autowired
    FileStorage fileStorage;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String execute(String ownerId){
        try {
            return fileStorage.readTextFile(ownerId + ".json");
        } catch (IllegalArgumentException e) {
            log.error("failed to serialize file contents, {}", e.getMessage());
            throw new RuntimeException();
        } catch (IOException e) {
            log.error("failed on read file, {}", e.getMessage());
            throw new RuntimeException();
        }
    }

}
