package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee get(Long id) {
        logger.info("Check if user exists with id={}", id);
        if (employeeRepository.existsByEmployeeId(id)) {
            logger.info("Get employee with id={}", id);
            return employeeRepository.getByEmployeeId(id);
        } else {
            logger.warn("Tried to get not existing employee with id={}", id);
            throw new EmployeeNotFoundException("Could not get employee with id = " + id);
        }
    }

    public List<Employee> getAll() {
        logger.info("Get all employees");
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        logger.info("Create employees");
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        logger.info("Check if user exists with id={}", employee.getEmployeeId());
        if (employeeRepository.existsByEmployeeId(employee.getEmployeeId())) {
            logger.info("Update employee with id={}", employee.getEmployeeId());
            return employeeRepository.save(employee);
        } else {
            logger.warn("Try to update not existing employee with id={}", employee.getEmployeeId());
            throw new EmployeeNotFoundException("Could not update employee with id = " + employee.getEmployeeId());
        }
    }

    @Transactional
    public void delete(Long id) {
        logger.info("Delete employee with id={}", id);
        employeeRepository.deleteByEmployeeId(id);
    }
}
