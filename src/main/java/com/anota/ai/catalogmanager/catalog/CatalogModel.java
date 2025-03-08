package com.anota.ai.catalogmanager.catalog;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogModel {

    private String owner;
    private List<Category> catalog;

    public record Category(
        @JsonProperty("category_title")
        String categoryTitle,
        @JsonProperty("category_description")
        String categoryDescription,
        List<CategoryItem> items
    ) {}

    public record CategoryItem(
        String title,
        String description,
        BigDecimal price
    ) {}

}
