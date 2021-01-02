package com.mastery.java.task.activemq;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class ReceiveService {

    private final Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    @Autowired
    private EmployeeService employeeService;

    @JmsListener(destination = "employee-base-queue")
    public void receiveMessage(String message) {
        logger.info("Received message: {}", message);
    }

    @JmsListener(destination = "employee-base-create-queue")
    @SendTo("employee-base-create-answer-queue")
    public Employee receiveData(ActiveMQObjectMessage message) throws JMSException {
        Employee employee = (Employee) message.getObject();
        logger.info("Received employee: {}", employee.toString());
        return employeeService.createEmployee(employee);
    }
}
