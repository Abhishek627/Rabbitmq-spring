package com.cisco.iot.rabbitmq;

public class AuditRabbitMQConstants {
    public static final int MAX_RETRY_COUNT = 10;
    /**
     * Configuration constants
     */
    public static final String RABBITMQ_HOST = "localhost";
    public static final String RABBITMQ_PORT = "5672";
    public static final String RABBITMQ_USERNAME = "guest";
    public static final String RABBITMQ_PASSWORD = "guest";
    public static final String RABBITMQ_VHOST = "/";
//    public static final String RABBITMQ_HOST = "localhost";
//    public static final String RABBITMQ_PORT = "8089";
//    public static final String RABBITMQ_USERNAME = "admin";
//    public static final String RABBITMQ_PASSWORD = "nNU4f~H8325%_QM9";
//    public static final String RABBITMQ_VHOST = "/";

    /**
     * Constant Strings
     */
    public static final String AUDIT_EXCHANGE_NAME = "rainier";
    public static final String AUDIT_EVENT_QUEUE = "audit_events";
    public static final String AUDIT_NOTIFICATION_QUEUE = "notification_events";
    public static final String EVENT_ROUTING_KEY = "event";
    public static final String NOTIFICATION_ROUTING_KEY = "notification";
    public static final String EVENT_BINDING_KEY = "event";
    public static final String NOTIFICATION_BINDING_KEY = "notification";

}
