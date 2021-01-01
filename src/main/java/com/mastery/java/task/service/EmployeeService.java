package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeBaseServiceException;
import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.rest.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        logger.info("Get all employees");
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(Long id) {
        logger.info("Delete employee with id={}", id);
        employeeRepository.deleteById(id);
    }

    public Employee getEmployee(Long id) {
        logger.info("Get employee with id={}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeBaseServiceException("Could not get employee with id = " + id));
    }

    public Employee createEmployee(Employee employee) {
        logger.info("Create " + employee.toString());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        logger.info("Update employee with id={}", employee.getEmployeeId());
        employee.setEmployeeId(id);
        return employeeRepository.save(employee);
    }
}
