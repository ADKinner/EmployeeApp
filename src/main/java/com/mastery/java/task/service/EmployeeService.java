package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    public void deleteEmployeeById(Long id) {
        employeeDao.delete(id);
    }

    public Employee getEmployee(Long id) {
        return employeeDao.get(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeDao.create(employee);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        employee.setEmployeeId(id);
        return employeeDao.update(employee);
    }
}
