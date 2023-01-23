package com.rabbitmq.notificationservice.dto;


import com.rabbitmq.notificationservice.dto.dto.CreateAccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void handleNotification(CreateAccountRequest createAccountRequest){
        log.info("Received Notification.. - {}", createAccountRequest.getBalance());
    }
}
