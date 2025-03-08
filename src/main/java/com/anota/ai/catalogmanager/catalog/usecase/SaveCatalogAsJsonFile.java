package com.anota.ai.catalogmanager.catalog.usecase;

import com.anota.ai.catalogmanager.catalog.CatalogModel;
import com.anota.ai.catalogmanager.catalog.service.FileStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SaveCatalogAsJsonFile {

    @Autowired
    FileStorage fileStorage;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void execute(CatalogModel catalog){
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = objectMapper.writeValueAsString(catalog);
            fileStorage.saveTextFile(json, catalog.getOwner() + ".json");
        } catch (JsonProcessingException e) {
            log.error("Fail on convert catalog class to json");
        } catch (IOException e) {
            log.error("Fail to write JSON file");
        }
    }

}
