package com.mastery.java.task.rest;

import com.mastery.java.task.activemq.MessageService;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get employee by his id",
            notes = "Provide an id to look up specific employee from employee base",
            response = Employee.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Employee Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Employee get(@PathVariable @Valid @PositiveOrZero Long id) {
        logger.info("Process get request (get employee by id={})", id);
        messageService.sendMessage("Get employee by his id: " + id);
        return employeeService.getEmployee(id);
    }

    @GetMapping
    @ApiOperation(
            value = "Get all employees",
            notes = "Provides nothing to look up all employees from employee base",
            response = Employee.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Employee> getAll() {
        logger.info("Process get request (get all employees)");
        messageService.sendMessage("Get employees");
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete employee by his id",
            notes = "Provide an id to delete specific employee from employee base",
            response = Employee.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void delete(@PathVariable @PositiveOrZero Long id) {
        logger.info("Process delete request (delete employee by id={})", id);
        messageService.sendMessage("Delete employee with id: " + id);
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Create employee by his data",
            notes = "Provide employee data to add employee in employee base",
            response = Employee.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Employee create(@RequestBody @Valid Employee employee) {
        logger.info(
                "Process post request (create employee with params: " +
                        "first_name={}, last_name={}, gender={}, department_id={}, job_title={}, date_of_birth={}",
                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth().toString()
        );
        return messageService.sendData(employee);
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update employee by his id and data",
            notes = "Provide an id and data to update specific employee in employee base",
            response = Employee.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Employee Not Found To Update"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Employee update(@RequestBody @Valid Employee employee, @PathVariable @Valid @PositiveOrZero Long id) {
        logger.info(
                "Process update request (update employee with id={} by params: " +
                        "first_name={}, last_name={}, gender={}, department_id={}, job_title={}, date_of_birth={}",
                id,
                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth().toString()
        );
        messageService.sendMessage("Update employee with id=" + id + ": " + employee.toString());
        return employeeService.updateEmployee(employee, id);
    }
}
