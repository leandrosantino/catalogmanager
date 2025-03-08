package com.anota.ai.catalogmanager.product.usecase;

import com.anota.ai.catalogmanager.product.ProductModel;
import com.anota.ai.catalogmanager.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAllProducts {

    @Autowired ProductRepository productRepository;

    public List<ProductModel> execute(){
        return productRepository.findAll();
    }

}
