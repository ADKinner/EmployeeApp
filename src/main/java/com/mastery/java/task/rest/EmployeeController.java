package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public Employee get(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee update(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.updateEmployee(employee, id);
    }
}
