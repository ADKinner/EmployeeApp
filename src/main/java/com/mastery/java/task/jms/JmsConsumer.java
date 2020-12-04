package com.mastery.java.task.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class JmsConsumer {

    private final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = "employee-message-queue")
    @SendTo("employee-message-answers-queue")
    public String receive(String message){
        logger.info("Received: " + message);
        return "Get: " + message;
    }
}
