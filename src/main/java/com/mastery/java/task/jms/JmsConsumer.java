package com.mastery.java.task.jms;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class JmsConsumer {

    private final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @Autowired
    private EmployeeService employeeService;

    @JmsListener(destination = "employee-get-queue")
    @SendTo("employee-get-answers-queue")
    public Employee receive(String id){
        logger.info("Received id: {}", id);
        return employeeService.getEmployee(Long.parseLong(id));
    }
}
