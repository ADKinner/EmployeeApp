package com.mastery.java.task.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class JmsConsumer {

    private final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = "employee-message-queue")
    public void receive(String message){
        logger.info("Received: " + message);
    }
}
