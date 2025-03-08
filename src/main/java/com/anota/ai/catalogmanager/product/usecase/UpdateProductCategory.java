package com.anota.ai.catalogmanager.product.usecase;

import com.anota.ai.catalogmanager.category.CategoryRepository;
import com.anota.ai.catalogmanager.category.exceptions.CategoryNotFoundException;
import com.anota.ai.catalogmanager.product.ProductModel;
import com.anota.ai.catalogmanager.product.ProductRepository;
import com.anota.ai.catalogmanager.product.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateProductCategory {

    @Autowired ProductRepository productRepository;
    @Autowired CategoryRepository categoryRepository;

    public void execute( String productId, String categoryId ) {
        ProductModel product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        if(product.getCategoryId().equals(categoryId)) return;
        if(!categoryRepository.existsById(categoryId)) throw new CategoryNotFoundException();

        product.setCategoryId(categoryId);
        productRepository.save(product);
    }

}
