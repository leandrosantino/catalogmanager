package com.anota.ai.catalogmanager.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
    List<ProductModel> findByCategoryId(String categoryId);
    Integer countByCategoryId(String categoryID);
}
