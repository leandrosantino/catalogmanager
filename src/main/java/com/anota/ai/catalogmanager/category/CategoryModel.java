package com.anota.ai.catalogmanager.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@AllArgsConstructor
@Document(collection = "category")
public class CategoryModel {

    @Id
    String id;
    String title;
    String description;
    String ownerId;

}
