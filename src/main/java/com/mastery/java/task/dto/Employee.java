package com.mastery.java.task.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "employees")
@ApiModel(description = "Details about employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Unique id of the employee")
    private Long employeeId;

    @Column(nullable = false, name = "first_name")
    @ApiModelProperty(notes = "Employee's name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    @ApiModelProperty(notes = "Employee's surname")
    private String lastName;

    @Column(nullable = false, name = "department_id")
    @ApiModelProperty(notes = "Id of employee's department")
    private Long departmentId;

    @Column(nullable = false, name = "job_title")
    @ApiModelProperty(notes = "Employee's job title")
    private String jobTitle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Employee's gender")
    private Gender gender;

    @Column(nullable = false, name = "date_of_birth")
    @ApiModelProperty(notes = "Employee's birth date")
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Long departmentId, String jobTitle, Gender gender,
                    LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(Long employeeId, String firstName, String lastName, Long departmentId, String jobTitle,
                    Gender gender, LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
