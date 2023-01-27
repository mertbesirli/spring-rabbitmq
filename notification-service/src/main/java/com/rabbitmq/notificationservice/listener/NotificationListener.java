package com.rabbitmq.notificationservice.listener;

import com.rabbitmq.notificationservice.dto.CreateAccountRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationListener {
    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void handleNotification(CreateAccountRequest createAccountRequest){
        log.info("Received Notification.. - {}", createAccountRequest.getBalance());
    }

}
