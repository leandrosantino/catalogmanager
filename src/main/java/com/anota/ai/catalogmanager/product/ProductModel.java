package com.anota.ai.catalogmanager.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
@Document(collection = "product")
public class ProductModel {

    @Id
    String id;
    String title;
    String description;
    String categoryId;
    String ownerId;

    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal price;

}
