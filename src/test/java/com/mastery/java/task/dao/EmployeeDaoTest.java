package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeDaoTest {

    private static final Long EMPLOYEE_ID = 1L;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeDao employeeDao;

    @Test
    public void testGetAll() {
        employeeDao.getAll();
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testSuccessfulGet() throws EmployeeNotFoundException {
        Mockito.when(employeeRepository.existsByEmployeeId(EMPLOYEE_ID)).thenReturn(true);
        Mockito.when(employeeRepository.getByEmployeeId(EMPLOYEE_ID)).thenReturn(Mockito.mock(Employee.class));
        employeeDao.get(EMPLOYEE_ID);
        Mockito.verify(employeeRepository, Mockito.times(1)).existsByEmployeeId(EMPLOYEE_ID);
        Mockito.verify(employeeRepository, Mockito.times(1)).getByEmployeeId(EMPLOYEE_ID);
    }

    @Test
    public void testUnsuccessfulGet() {
        Mockito.when(employeeRepository.existsByEmployeeId(EMPLOYEE_ID))
                .thenReturn(false)
                .thenThrow(new EmployeeNotFoundException("Could not get employee with id = " + EMPLOYEE_ID));
        try {
            employeeDao.get(EMPLOYEE_ID);
        } catch (Exception e) {
            Assertions.assertEquals(
                    "Could not get employee with id = " + EMPLOYEE_ID,
                    e.getMessage()
            );
            Mockito.verify(employeeRepository, Mockito.times(1)).existsByEmployeeId(EMPLOYEE_ID);
            Mockito.verify(employeeRepository, Mockito.times(0)).getOne(EMPLOYEE_ID);
        }
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = Mockito.mock(Employee.class);
        employeeDao.create(employee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    public void testSuccessfulUpdateEmployee() throws EmployeeNotFoundException {
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeRepository.existsByEmployeeId(employee.getEmployeeId())).thenReturn(true);
        employeeDao.update(employee);
        Mockito.verify(employeeRepository, Mockito.times(1))
                .existsByEmployeeId(employee.getEmployeeId());
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    public void testUnsuccessfulUpdateEmployee() {
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeRepository.existsByEmployeeId(employee.getEmployeeId()))
                .thenReturn(false)
                .thenThrow(
                        new EmployeeNotFoundException("Could not update employee with id = " + employee.getEmployeeId())
                );
        try {
            employeeDao.update(employee);
        } catch (Exception e) {
            Assertions.assertEquals(
                    "Could not update employee with id = " + employee.getEmployeeId(),
                    e.getMessage()
            );
            Mockito.verify(employeeRepository, Mockito.times(1))
                    .existsByEmployeeId(employee.getEmployeeId());
            Mockito.verify(employeeRepository, Mockito.times(0)).save(employee);
        }
    }

    @Test
    public void testDeleteEmployees() {
        employeeDao.delete(EMPLOYEE_ID);
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteByEmployeeId(EMPLOYEE_ID);
    }
}
