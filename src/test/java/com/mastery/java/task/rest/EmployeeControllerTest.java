package com.mastery.java.task.rest;

import com.mastery.java.task.activemq.MessageService;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private static final Long EMPLOYEE_ID = 1L;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private MessageService messageService;

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
        Mockito.when(employee.getGender()).thenReturn(Gender.MALE);
        Mockito.when(employee.getDateOfBirth()).thenReturn(LocalDate.now());
        employeeController.create(employee);
        Mockito.verify(employeeService, Mockito.times(1)).createEmployee(employee);
    }

    @Test
    public void testUpdate() {
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getGender()).thenReturn(Gender.MALE);
        Mockito.when(employee.getDateOfBirth()).thenReturn(LocalDate.now());
        employeeController.update(employee, EMPLOYEE_ID);
        Mockito.verify(employeeService, Mockito.times(1)).updateEmployee(employee, EMPLOYEE_ID);
    }

    @Test
    public void testDelete() {
        employeeController.delete(EMPLOYEE_ID);
        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployeeById(EMPLOYEE_ID);
    }
}
