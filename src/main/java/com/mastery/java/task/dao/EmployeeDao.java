package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee get(Long id) {
        if (employeeRepository.existsByEmployeeId(id)) {
            return employeeRepository.getByEmployeeId(id);
        } else {
            throw new EmployeeNotFoundException("Could not get employee with id = " + id);
        }
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        if (employeeRepository.existsByEmployeeId(employee.getEmployeeId())) {
            return employeeRepository.save(employee);
        } else {
            throw new EmployeeNotFoundException("Could not update employee with id = " + employee.getEmployeeId());
        }
    }

    public void delete(Long id) {
        employeeRepository.deleteByEmployeeId(id);
    }
}
