package com.anota.ai.catalogmanager.category.usecase;

import com.anota.ai.catalogmanager.category.CategoryRepository;
import com.anota.ai.catalogmanager.category.exceptions.CategoryNotDeletableException;
import com.anota.ai.catalogmanager.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategory {

    @Autowired CategoryRepository categoryRepository;
    @Autowired ProductRepository productRepository;

    public void execute(String id){
        Integer countProductsOfCategory = productRepository.countByCategoryId(id);
        if (countProductsOfCategory > 0){
            throw new CategoryNotDeletableException("Category cannot be deleted because it has assigned products");
        }
        categoryRepository.deleteById(id);
    }

}
