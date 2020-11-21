package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get employee by his id",
            notes = "Provide an id to look up specific employee from employee base",
            response = Employee.class
    )
    public Employee get(@PathVariable Long id) {
        logger.info("Process get request (get employee by id={})", id);
        return employeeService.getEmployee(id);
    }

    @GetMapping
    @ApiOperation(
            value = "Get all employees",
            notes = "Provides nothing to look up all employees from employee base",
            response = Employee.class
    )
    public List<Employee> getAll() {
        logger.info("Process get request (get all employees)");
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete employee by his id",
            notes = "Provide an id to delete specific employee from employee base",
            response = Employee.class
    )
    public void delete(@PathVariable Long id) {
        logger.info("Process delete request (delete employee by id={})", id);
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Create employee by his data",
            notes = "Provide employee data to add employee in employee base",
            response = Employee.class
    )
    public Employee create(@RequestBody @Valid Employee employee) {
        logger.info(
                "Process post request (create employee with params: " +
                        "first_name={}, last_name={}, gender={}, department_id={}, job_title={}, date_of_birth={}",
                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth().toString()
        );
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update employee by his id and data",
            notes = "Provide an id and data to update specific employee in employee base",
            response = Employee.class
    )
    public Employee update(@RequestBody @Valid Employee employee, @PathVariable Long id) {
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
