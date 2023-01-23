package com.rabbitmq.accountservice.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    String host;
    @Value("${spring.rabbitmq.username}")
    String username;
    @Value("${spring.rabbitmq.password}")
    String password;
    @Value("${sample.rabbitmq.exchange}")
    String exchange;
    @Value("${sample.rabbitmq.queue}")
    String queueName;
    @Value("${sample.rabbitmq.routingKey}")
    String routingKey;

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }
    @Bean
    Queue stepQueue(){
        return new Queue(queueName, false);
    }
    @Bean
    Binding binding(Queue stepQueue, DirectExchange exchange){
        return BindingBuilder.bind(stepQueue).to(exchange).with(routingKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


}
