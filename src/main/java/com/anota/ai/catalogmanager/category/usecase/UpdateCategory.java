package com.anota.ai.catalogmanager.category.usecase;

import com.anota.ai.catalogmanager.category.CategoryModel;
import com.anota.ai.catalogmanager.category.CategoryRepository;
import com.anota.ai.catalogmanager.category.dto.CategoryDTO;
import com.anota.ai.catalogmanager.category.exceptions.CategoryNotFoundException;
import com.anota.ai.catalogmanager.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategory {

    @Autowired CategoryRepository categoryRepository;

    public void execute(String id, CategoryDTO category){
        CategoryModel savedCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        CategoryModel updateCategory = category.toModel();

        ObjectUtils.copyNonNullProperties(updateCategory, savedCategory);

        categoryRepository.save(savedCategory);
    }

}
