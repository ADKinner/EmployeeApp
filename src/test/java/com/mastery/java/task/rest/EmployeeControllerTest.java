package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private static final Long EMPLOYEE_ID = 1L;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGet() {
        employeeController.get(EMPLOYEE_ID);
        Mockito.verify(employeeService, Mockito.times(1)).getEmployee(EMPLOYEE_ID);
    }

    @Test
    public void testGetAll() {
        employeeController.getAll();
        Mockito.verify(employeeService, Mockito.times(1)).getAllEmployees();
    }

    @Test
    public void testCreate() {
        Employee employee = Mockito.mock(Employee.class);
        employeeController.create(employee);
        Mockito.verify(employeeService, Mockito.times(1)).createEmployee(employee);
    }

    @Test
    public void testUpdate() {
        Employee employee = Mockito.mock(Employee.class);
        employeeController.update(employee, EMPLOYEE_ID);
        Mockito.verify(employeeService, Mockito.times(1)).updateEmployee(employee, EMPLOYEE_ID);
    }

    @Test
    public void testDelete() {
        employeeController.delete(EMPLOYEE_ID);
        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployeeById(EMPLOYEE_ID);
    }
}
