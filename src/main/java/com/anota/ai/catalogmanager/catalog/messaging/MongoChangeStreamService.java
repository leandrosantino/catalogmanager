package com.anota.ai.catalogmanager.catalog.messaging;

import com.anota.ai.catalogmanager.category.CategoryModel;
import com.anota.ai.catalogmanager.product.ProductModel;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocumentBeforeChange;
import com.mongodb.client.model.changestream.OperationType;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class MongoChangeStreamService {

    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private CatalogEmitPublisher catalogEmitPublisher;

    @PostConstruct
    public void init() {
        enablePreImagesForCollection("product");
        enablePreImagesForCollection("category");

        setHandler(this::productHandleChange, "product");
        setHandler(this::categoryHandleChange, "category");
    }

    private void productHandleChange(Document document) {
        ProductModel product = mongoTemplate.getConverter().read(ProductModel.class, document);
        catalogEmitPublisher.publish(product.getOwnerId());
    }

    private void categoryHandleChange(Document document) {
        CategoryModel category = mongoTemplate.getConverter().read(CategoryModel.class, document);
        catalogEmitPublisher.publish(category.getOwnerId());
    }

    private void setHandler(Consumer<Document> handler, String collectionName) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);
        List<Bson> pipeline = List.of(
            Aggregates.match(
                Filters.in("operationType", Arrays.asList("insert", "replace", "update", "delete"))
            )
        );
        ChangeStreamIterable<Document> changeStream = collection.watch(pipeline)
                .fullDocumentBeforeChange(FullDocumentBeforeChange.WHEN_AVAILABLE);

        new Thread(() -> changeStream.forEach(event -> {
            Document document = event.getOperationType() == OperationType.DELETE
                    ?event.getFullDocumentBeforeChange()
                    :event.getFullDocument();
            if (document == null) return;
            handler.accept(document);
        })).start();
    }

    private void enablePreImagesForCollection(String collectionName) {
        Document command = new Document("collMod", collectionName)
                .append("changeStreamPreAndPostImages", new Document("enabled", true));
        Document result = mongoTemplate.getDb().runCommand(command);
        System.out.println("Pre-Images enabled for " + collectionName + ": " + result.toJson());
    }

}
