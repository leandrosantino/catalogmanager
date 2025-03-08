package com.anota.ai.catalogmanager.product.usecase;

import com.anota.ai.catalogmanager.category.CategoryRepository;
import com.anota.ai.catalogmanager.category.exceptions.CategoryNotFoundException;
import com.anota.ai.catalogmanager.product.ProductModel;
import com.anota.ai.catalogmanager.product.ProductRepository;
import com.anota.ai.catalogmanager.product.dto.ProductDTO;
import com.anota.ai.catalogmanager.product.exception.ProductNotFoundException;
import com.anota.ai.catalogmanager.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProduct {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;

    public void execute(String id, ProductDTO product){
        if (product.categoryId() != null ) {
            if (!categoryRepository.existsById(product.categoryId())){
                throw new CategoryNotFoundException();
            }
        }

        ProductModel savedProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        ProductModel updatedProduct = product.toModel();

        ObjectUtils.copyNonNullProperties(updatedProduct, savedProduct);

        productRepository.save(savedProduct);

    }

}
