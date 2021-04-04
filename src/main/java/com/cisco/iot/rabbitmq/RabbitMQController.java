package com.cisco.iot.rabbitmq;

import com.cisco.iot.rabbitmq.Event;
import com.cisco.iot.rabbitmq.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/rabbitmq/")
public class RabbitMQController {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

    @Autowired
    private AmqpTemplate rabbitTemplateBean;


    @Autowired
    private Producer Producer;

    //direct
    //http://localhost:8080/api/rabbitmq/producer?code=001&description=MyDescription
    @GetMapping(value = "/producer")
    public String producer(@ModelAttribute Event event) {
        logger.info(" event {}" ,event );
        Producer.sendMessage(event);

        return "Message sent to the RabbitMQ Successfully";
    }


    //http://localhost:8080/api/rabbitmq/topic?exchangeName=topic-exchange&routingKey=queue.admin&messageData=MessageData
    @GetMapping(value = "/exchange")
    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                           @RequestParam("messageData") String messageData) {

        rabbitTemplateBean.convertAndSend(exchange, routingKey, messageData);

        return "Message sent to the RabbitMQ Successfully";
    }
}