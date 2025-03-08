package com.anota.ai.catalogmanager.catalog.usecase;

import com.anota.ai.catalogmanager.catalog.CatalogModel;
import com.anota.ai.catalogmanager.category.CategoryModel;
import com.anota.ai.catalogmanager.category.CategoryRepository;
import com.anota.ai.catalogmanager.product.ProductModel;
import com.anota.ai.catalogmanager.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenerateCatalog {

    @Autowired ProductRepository productRepository;
    @Autowired CategoryRepository categoryRepository;

    public CatalogModel execute(String ownerId) {

        CatalogModel catalogModel = new CatalogModel();
        List<CatalogModel.Category> catalog = new ArrayList<>();

        List<CategoryModel> categories = categoryRepository.findByOwnerId(ownerId);
        categories.forEach(category -> {
            List<CatalogModel.CategoryItem> items = new ArrayList<>();
            List<ProductModel> products = productRepository.findByCategoryId(category.getId());

            products.forEach(product -> {
                CatalogModel.CategoryItem item = new CatalogModel.CategoryItem(
                    product.getTitle(),
                    product.getDescription(),
                    product.getPrice()
                );
                items.add(item);
            });

            CatalogModel.Category catalogCategory = new CatalogModel.Category(
                category.getTitle(),
                category.getDescription(),
                items
            );

            catalog.add(catalogCategory);
        });

        catalogModel.setOwner(ownerId);
        catalogModel.setCatalog(catalog);

        return catalogModel;
    }

}
