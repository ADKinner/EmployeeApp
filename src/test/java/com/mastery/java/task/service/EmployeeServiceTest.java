package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private static final Long EMPLOYEE_ID = 1L;

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Test
    public void testGetAllEmployees() {
        employeeService.getAllEmployees();
        Mockito.verify(employeeDao, Mockito.times(1)).getAll();
    }

    @Test
    public void testGetEmployee() {
        employeeService.getEmployee(EMPLOYEE_ID);
        Mockito.verify(employeeDao, Mockito.times(1)).get(EMPLOYEE_ID);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = Mockito.mock(Employee.class);
        employeeService.createEmployee(employee);
        Mockito.verify(employeeDao, Mockito.times(1)).create(employee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = Mockito.mock(Employee.class);
        employeeService.updateEmployee(employee, EMPLOYEE_ID);
        Mockito.verify(employee).setEmployeeId(EMPLOYEE_ID);
        Mockito.verify(employeeDao, Mockito.times(1)).update(employee);
    }

    @Test
    public void testDeleteEmployees() {
        employeeService.deleteEmployeeById(EMPLOYEE_ID);
        Mockito.verify(employeeDao, Mockito.times(1)).delete(EMPLOYEE_ID);
    }
}
