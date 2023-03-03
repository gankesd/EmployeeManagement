package com.example.employeemanagement.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}