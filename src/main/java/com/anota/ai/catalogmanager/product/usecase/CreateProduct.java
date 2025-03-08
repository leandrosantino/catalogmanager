package com.anota.ai.catalogmanager.product.usecase;

import com.anota.ai.catalogmanager.category.CategoryRepository;
import com.anota.ai.catalogmanager.category.exceptions.CategoryNotFoundException;
import com.anota.ai.catalogmanager.product.ProductModel;
import com.anota.ai.catalogmanager.product.ProductRepository;
import com.anota.ai.catalogmanager.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProduct {

    @Autowired ProductRepository productRepository;
    @Autowired CategoryRepository categoryRepository;

    public ProductModel execute(ProductDTO createProductData){
        if( !categoryRepository.existsById(createProductData.categoryId()) ) {
            throw new CategoryNotFoundException();
        }

        ProductModel product = new ProductModel(
            null,
            createProductData.title(),
            createProductData.description(),
            createProductData.categoryId(),
            createProductData.ownerId(),
            createProductData.price()
        );

        return productRepository.save(product);
    }

}
