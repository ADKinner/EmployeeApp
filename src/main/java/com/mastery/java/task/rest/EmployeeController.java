package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        logger.info("Process get request (get employee by id={})", id);
        return employeeService.getEmployee(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        logger.info("Process get request (get all employees)");
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Process delete request (delete employee by id={})", id);
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        logger.info(
                "Process post request (create employee with params: " +
                        "first_name={}, last_name={}, gender={}, department_id={}, job_title={}, date_of_birth={}",
                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth().toString()
        );
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@RequestBody Employee employee, @PathVariable Long id) {
        logger.info(
                "Process update request (update employee with id={} by params: " +
                        "first_name={}, last_name={}, gender={}, department_id={}, job_title={}, date_of_birth={}",
                id,
                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth().toString()
        );
        return employeeService.updateEmployee(employee, id);
    }
}
