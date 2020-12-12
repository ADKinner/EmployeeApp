package com.mastery.java.task.activemq;

import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("employee-base-queue")
    private String jmsQueue;

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("employee-base-queue", message);
    }
}
