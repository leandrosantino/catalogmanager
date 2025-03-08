package com.anota.ai.catalogmanager.category.controller;

import com.anota.ai.catalogmanager.category.CategoryModel;
import com.anota.ai.catalogmanager.category.dto.CategoryDTO;
import com.anota.ai.catalogmanager.category.usecase.CreateCategory;
import com.anota.ai.catalogmanager.category.usecase.DeleteCategory;
import com.anota.ai.catalogmanager.category.usecase.ListAllCategories;
import com.anota.ai.catalogmanager.category.usecase.UpdateCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired CreateCategory createCategoryUseCase;
    @Autowired UpdateCategory updateCategoryUseCase;
    @Autowired DeleteCategory deleteCategoryUseCase;
    @Autowired ListAllCategories listAllCategoriesUseCase;

    @GetMapping
    public List<CategoryModel> listAllCategories(){
        return listAllCategoriesUseCase.execute();
    }

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryDTO createCategoryData){
        CategoryModel createdCategory = createCategoryUseCase.execute(createCategoryData);
        return ResponseEntity.ok().body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(
        @RequestBody CategoryDTO updateCategoryData,
        @PathVariable String id
    ){
        updateCategoryUseCase.execute(id, updateCategoryData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryModel> deleteCategory( @PathVariable String id ){
        deleteCategoryUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

}
