package com.anota.ai.catalogmanager.product.usecase;

import com.anota.ai.catalogmanager.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteProduct {

    @Autowired ProductRepository productRepository;

    public void execute(String id) {
        productRepository.deleteById(id);
    }

}
