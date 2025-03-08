package com.anota.ai.catalogmanager.category.dto;

import com.anota.ai.catalogmanager.category.CategoryModel;

public record CategoryDTO(
    String title,
    String description,
    String ownerId
) {

    public CategoryModel toModel(){
        return new CategoryModel(
            null,
            title(),
            description(),
            ownerId()
        );
    }

}
