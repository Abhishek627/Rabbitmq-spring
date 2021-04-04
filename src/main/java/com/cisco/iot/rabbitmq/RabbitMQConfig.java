package com.cisco.iot.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private static final Logger logger = LoggerFactory.getLogger(
            RabbitMQConfig.class);

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(AuditRabbitMQConstants.RABBITMQ_HOST);
        factory.setVirtualHost(AuditRabbitMQConstants.RABBITMQ_VHOST);
        factory.setUsername(AuditRabbitMQConstants.RABBITMQ_USERNAME);
        factory.setPassword(AuditRabbitMQConstants.RABBITMQ_PASSWORD);
        factory.setPort(Integer.parseInt(AuditRabbitMQConstants.RABBITMQ_PORT));
        logger.info("RMQ connection to {} on port {} and vhost {} with username {} is successful",AuditRabbitMQConstants.RABBITMQ_HOST,
                AuditRabbitMQConstants.RABBITMQ_PORT, AuditRabbitMQConstants.RABBITMQ_VHOST,  AuditRabbitMQConstants.RABBITMQ_USERNAME,
                AuditRabbitMQConstants.RABBITMQ_PASSWORD);
        return factory;
    }


    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange exchange(){
        logger.info("Created rabbitmq exchange : {}", AuditRabbitMQConstants.AUDIT_EXCHANGE_NAME);
        return new DirectExchange(AuditRabbitMQConstants.AUDIT_EXCHANGE_NAME);
    }

    @Bean
    public Queue eventQueue() {
        logger.info("Created rabbitmq queue: {}", AuditRabbitMQConstants.AUDIT_EVENT_QUEUE);
        return new Queue(AuditRabbitMQConstants.AUDIT_EVENT_QUEUE, true);
    }

    @Bean
    public Queue notificationQueue() {
        logger.info("Created rabbitmq queue: {}", AuditRabbitMQConstants.AUDIT_NOTIFICATION_QUEUE);
        return new Queue(AuditRabbitMQConstants.AUDIT_NOTIFICATION_QUEUE, true);
    }


    @Bean
    public Binding eventbinding(Queue eventQueue, DirectExchange exchange){
        logger.info("Created rabbitmq binding for queue {} with exchange using binding key {}", eventQueue,
                exchange, AuditRabbitMQConstants.EVENT_BINDING_KEY);
        return BindingBuilder.bind(eventQueue).to(exchange).with(AuditRabbitMQConstants.EVENT_BINDING_KEY);
    }

    @Bean
    public Binding notificationbinding(Queue notificationQueue, DirectExchange exchange){
        logger.info("Created rabbitmq binding for queue {} with exchange using binding key {}", notificationQueue,
                exchange, AuditRabbitMQConstants.NOTIFICATION_BINDING_KEY);
        return BindingBuilder.bind(notificationQueue).to(exchange).with(AuditRabbitMQConstants.NOTIFICATION_BINDING_KEY);
    }


}
