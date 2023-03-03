package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.RequestEmployeeDTO;
import com.example.employeemanagement.dto.ResponseEmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<ResponseEmployeeDTO> getAllEmployees();

    ResponseEmployeeDTO getEmployeeById(int empId);

    ResponseEmployeeDTO updateEmployee(int empId, RequestEmployeeDTO requestEmployeeDTO);

    ResponseEmployeeDTO deleteEmployee(int empId);

    ResponseEmployeeDTO createEmployee(RequestEmployeeDTO requestEmployeeDTO);
}


