package com.anota.ai.catalogmanager.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel, String> {
    List<CategoryModel> findByOwnerId(String ownerId);
}
