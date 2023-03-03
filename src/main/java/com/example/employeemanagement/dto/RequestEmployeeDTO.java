package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEmployeeDTO {

    private String firstName;
    private String lastName;
    private String department;
    private String designation;
    private double salary;

}
