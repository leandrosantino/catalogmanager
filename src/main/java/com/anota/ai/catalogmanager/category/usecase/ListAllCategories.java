package com.anota.ai.catalogmanager.category.usecase;

import com.anota.ai.catalogmanager.category.CategoryModel;
import com.anota.ai.catalogmanager.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAllCategories {

    @Autowired CategoryRepository categoryRepository;

    public List<CategoryModel> execute(){
        return categoryRepository.findAll();
    }

}
