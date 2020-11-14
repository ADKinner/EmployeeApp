package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
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
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        logger.info("Process getting all employees");
        return employeeDao.getAll();
    }

    public void deleteEmployeeById(Long id) {
        logger.info("Process deleting employee by id={}", id);
        employeeDao.delete(id);
    }

    public Employee getEmployee(Long id) {
        logger.info("Process getting employee by id={}", id);
        return employeeDao.get(id);
    }

    public Employee createEmployee(Employee employee) {
        logger.info("Process creating employee");
        return employeeDao.create(employee);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        logger.info("Process updating employee with id={}", id);
        employee.setEmployeeId(id);
        return employeeDao.update(employee);
    }
}
