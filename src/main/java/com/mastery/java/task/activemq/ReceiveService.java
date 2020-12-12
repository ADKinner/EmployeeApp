package com.mastery.java.task.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

    private final Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    @JmsListener(destination = "employee-base-queue")
    public void receiveMessage(String message) {
        logger.info("Received message: {}", message);
    }
}
