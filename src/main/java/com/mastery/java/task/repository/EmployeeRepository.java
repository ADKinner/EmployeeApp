package com.mastery.java.task.repository;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmployeeId(Long id);

    Employee getByEmployeeId(Long id);

    List<Employee> findAll();

    void deleteByEmployeeId(Long id);
}
