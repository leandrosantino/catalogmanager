package com.anota.ai.catalogmanager.product.controller;


import com.anota.ai.catalogmanager.product.ProductModel;
import com.anota.ai.catalogmanager.product.dto.ProductDTO;
import com.anota.ai.catalogmanager.product.dto.UpdateProductCategoryRequestDTO;
import com.anota.ai.catalogmanager.product.usecase.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {

    @Autowired CreateProduct createProductUseCase;
    @Autowired UpdateProductCategory updateProductCategoryUseCase;
    @Autowired UpdateProduct updateProductUseCase;
    @Autowired DeleteProduct deleteProductUseCase;
    @Autowired ListAllProducts listAllProductsUseCase;

    @GetMapping
    public List<ProductModel> listAllProducts() {
        return listAllProductsUseCase.execute();
    }

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductDTO createProductData) {
        ProductModel createdProduct = createProductUseCase.execute(createProductData);
        return ResponseEntity.ok().body(createdProduct);
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<Void> updateProductCategory(
        @RequestBody UpdateProductCategoryRequestDTO updateProductCategoryData,
        @PathVariable String id
    ){
        updateProductCategoryUseCase.execute(id, updateProductCategoryData.categoryId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
        @RequestBody ProductDTO updateProductData,
        @PathVariable String id
    ){
        updateProductUseCase.execute(id, updateProductData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
        @PathVariable String id
    ){
        deleteProductUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}
