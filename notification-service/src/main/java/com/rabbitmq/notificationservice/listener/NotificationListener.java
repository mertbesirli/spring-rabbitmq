package com.rabbitmq.notificationservice.listener;

import com.rabbitmq.notificationservice.dto.CreateAccountRequest;
import com.rabbitmq.notificationservice.event.OrderPlacedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationListener {
    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void handleNotification(CreateAccountRequest createAccountRequest){
        log.info("Received Notification RabbitMQ.. - {}", createAccountRequest.getBalance());
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent){
        // Send simple message, should be email notification, normally.
        log.info("Received Notification Apache Kafka.. - {}", orderPlacedEvent.getBalance());
    }

}
