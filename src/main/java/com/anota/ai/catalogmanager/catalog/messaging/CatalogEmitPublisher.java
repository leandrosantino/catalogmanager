package com.anota.ai.catalogmanager.catalog.messaging;

import com.anota.ai.catalogmanager.configuration.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CatalogEmitPublisher {

    @Autowired private RabbitTemplate rabbitTemplate;

    public void publish(String ownerId) {
        CatalogEmitMessage message = new CatalogEmitMessage(ownerId);
        rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME, message);
        log.info("notified queue catalog_emit, owner: {}", ownerId);
    }

}
