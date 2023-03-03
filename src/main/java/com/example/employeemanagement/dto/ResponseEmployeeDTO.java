package com.example.employeemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.employeemanagement.model.Employee;

@Getter
@Setter
@NoArgsConstructor

public class ResponseEmployeeDTO {

    private long employeeId;
    private String firstName;
    private String lastName;
    private String department;
    private String designation;
    private double salary;

    public ResponseEmployeeDTO(Employee employee) {
        this.employeeId=employee.getEmployeeId();
        this.firstName=employee.getFirstName();
        this.lastName=employee.getLastName();
        this.department=employee.getDepartment();
        this.designation=employee.getDesignation();
        this.salary=employee.getSalary();
    }
}
