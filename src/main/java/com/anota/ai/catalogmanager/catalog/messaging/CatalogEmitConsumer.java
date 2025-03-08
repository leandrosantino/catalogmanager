package com.anota.ai.catalogmanager.catalog.messaging;

import com.anota.ai.catalogmanager.catalog.CatalogModel;
import com.anota.ai.catalogmanager.catalog.usecase.GenerateCatalog;
import com.anota.ai.catalogmanager.catalog.usecase.SaveCatalogAsJsonFile;
import com.anota.ai.catalogmanager.configuration.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CatalogEmitConsumer {

    @Autowired private GenerateCatalog generateCatalog;
    @Autowired private SaveCatalogAsJsonFile saveCatalogAsJsonFile;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void run(Message<CatalogEmitMessage> message){
        CatalogModel catalog = generateCatalog.execute(message.getPayload().owner());
        saveCatalogAsJsonFile.execute(catalog);
        log.info("Generate catalog to owner: {}", message.getPayload().owner());
    }

}
