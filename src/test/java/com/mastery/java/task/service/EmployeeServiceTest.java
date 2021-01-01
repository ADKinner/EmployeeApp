package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeBaseServiceException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private static final Long EMPLOYEE_ID = 1L;

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetAllEmployees() {
        employeeService.getAllEmployees();
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testSuccessfulGet() {
        Mockito.when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(Mockito.mock(Employee.class)));
        employeeService.getEmployee(EMPLOYEE_ID);
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(EMPLOYEE_ID);
    }

    @Test
    public void testUnsuccessfulGet() {
        Mockito.when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.empty());
        try {
            employeeService.getEmployee(EMPLOYEE_ID);
        } catch (EmployeeBaseServiceException exception) {
            Assertions.assertEquals(
                    "Could not get employee with id = " + EMPLOYEE_ID,
                    exception.getMessage()
            );
        }
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(EMPLOYEE_ID);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = Mockito.mock(Employee.class);
        employeeService.createEmployee(employee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = Mockito.mock(Employee.class);
        employeeService.updateEmployee(employee, EMPLOYEE_ID);
        Mockito.verify(employee).setEmployeeId(EMPLOYEE_ID);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    public void testDeleteEmployees() {
        employeeService.deleteEmployeeById(EMPLOYEE_ID);
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(EMPLOYEE_ID);
    }
}
