package com.mastery.java.task.activemq;

import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("employee-base-queue", message);
    }

    public Employee sendData(Employee employee) {
        jmsTemplate.convertAndSend("employee-base-create-queue", employee);
        return (Employee) jmsTemplate.receiveAndConvert("employee-base-create-answer-queue");
    }
}
