package com.anota.ai.catalogmanager.product.dto;

import com.anota.ai.catalogmanager.product.ProductModel;

import java.math.BigDecimal;

public record ProductDTO(
    String title,
    String description,
    String categoryId,
    String ownerId,
    BigDecimal price
) {

    public ProductModel toModel(){
        return new ProductModel(
            null,
            title(),
            description(),
            categoryId(),
            ownerId(),
            price()
        );
    }

}
