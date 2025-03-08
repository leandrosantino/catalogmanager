package com.anota.ai.catalogmanager.catalog.controller;

import com.anota.ai.catalogmanager.catalog.CatalogModel;
import com.anota.ai.catalogmanager.catalog.usecase.GetCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalog")
public class CatalogController {

    @Autowired GetCatalog getCatalog;

    @GetMapping("/{ownerId}")
    public ResponseEntity<String> getCatalog(@PathVariable String ownerId){
        String catalog = getCatalog.execute(ownerId);
        return ResponseEntity.ok().body(catalog);
    }

}
