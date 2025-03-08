package com.anota.ai.catalogmanager.category.usecase;

import com.anota.ai.catalogmanager.category.CategoryModel;
import com.anota.ai.catalogmanager.category.CategoryRepository;
import com.anota.ai.catalogmanager.category.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCategory {

    @Autowired CategoryRepository categoryRepository;

    public CategoryModel execute(CategoryDTO createCategoryData) {
        CategoryModel category = new CategoryModel(
                null,
                createCategoryData.title(),
                createCategoryData.description(),
                createCategoryData.ownerId()
        );
        return categoryRepository.save(category);
    }

}
