package com.cisco.iot.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cisco.iot.rabbitmq.AuditRabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
//public class Listener {
//    private static final Logger logger = LoggerFactory.getLogger(Listener.class);
//
//
//    @RabbitListener(queues = AuditRabbitMQConstants.AUDIT_EVENT_QUEUE)
//    public void messageListener(Event event){
//
//        logger.info("Received Message from RabbitMQ Listener: {} and {}", event.getCode(), event.getDescription());
//    }
//}
