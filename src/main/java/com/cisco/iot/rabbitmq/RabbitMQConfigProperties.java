package com.cisco.iot.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQConfigProperties {
    private static final Logger logger = LoggerFactory.getLogger(
            RabbitMQConfigProperties.class);

    @Value("${host:" + AuditRabbitMQConstants.RABBITMQ_HOST + "}")
    private String host;

    public String getHost() {
        return host;
    }

    @Value("${port:AuditRabbitMQConstants.RABBITMQ_PORT}")
    private String port;

    @Value("${username:AuditRabbitMQConstants.RABBITMQ_USERNAME}")
    private String username;

    @Value("${password:AuditRabbitMQConstants.RABBITMQ_PASSWORD}")
    private String password;

    @Value("${vhost:AuditRabbitMQConstants.RABBITMQ_VHOST}")
    private String vhost;

    @Value("${exchange:testing}")
    private String exchange;

}
