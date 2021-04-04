package com.cisco.iot.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(Event event){
        RabbitMQConfigProperties rabbitconfig= new RabbitMQConfigProperties();
        logger.info("Exchange value is {} " , rabbitconfig.getHost());
        logger.info("Starting: Send Message to RabbitMQ.");
        rabbitTemplate.convertAndSend(AuditRabbitMQConstants.AUDIT_EXCHANGE_NAME, AuditRabbitMQConstants.EVENT_ROUTING_KEY, event);

        event.setCode("111111");
        rabbitTemplate.convertAndSend(AuditRabbitMQConstants.AUDIT_EXCHANGE_NAME, AuditRabbitMQConstants.NOTIFICATION_ROUTING_KEY, event);
        logger.info("Sent Message to RabbitMQ with payload: {}", event.toString());
    }
}