package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.RequestEmployeeDTO;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.employeemanagement.dto.ResponseEmployeeDTO;
import com.example.employeemanagement.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements com.example.employeemanagement.service.EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public List<ResponseEmployeeDTO> getAllEmployees() {
        List<Employee> employeesList = employeeRepo.findAll();
        List<ResponseEmployeeDTO> responseEmployeeDTOList = new ArrayList<>();
        responseEmployeeDTOList =  employeesList.stream().map((employee)->{
            ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO();
            responseEmployeeDTO.setEmployeeId(employee.getEmployeeId());
            responseEmployeeDTO.setFirstName(employee.getFirstName());
            responseEmployeeDTO.setLastName(employee.getLastName());
            responseEmployeeDTO.setDepartment(employee.getDepartment());
            responseEmployeeDTO.setDesignation(employee.getDesignation());
            responseEmployeeDTO.setSalary(employee.getSalary());
            return responseEmployeeDTO;
        }).collect(Collectors.toList());
        return responseEmployeeDTOList;

    }

    @Override
    public ResponseEmployeeDTO getEmployeeById(int empId) {
        Optional<Employee> employeeOptional = employeeRepo.findById(empId);
        if (employeeRepo.existsById(empId)) {
            Employee employee = employeeOptional.get();
            ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO(employee);
            return responseEmployeeDTO;
        }
        else {
            throw new EmployeeNotFoundException("Search: Employee ID " +empId+ " not found");
        }
    }

    @Override
    public ResponseEmployeeDTO updateEmployee(int empId, RequestEmployeeDTO requestEmployeeDTO) {
        Optional<Employee> employeeOptional = employeeRepo.findById(empId);
        if (employeeRepo.existsById(empId)){
            Employee employee = employeeOptional.get();
            employee.setFirstName(requestEmployeeDTO.getFirstName());
            employee.setLastName(requestEmployeeDTO.getLastName());
            employee.setDepartment(requestEmployeeDTO.getDepartment());
            employee.setDesignation(requestEmployeeDTO.getDesignation());
            employee.setSalary(requestEmployeeDTO.getSalary());
            employeeRepo.flush();
            ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO(employee);
            return responseEmployeeDTO;

        }else {
            return createEmployee(requestEmployeeDTO);
        }
    }

    @Override
    public ResponseEmployeeDTO deleteEmployee(int empId) {
        Optional<Employee> employeeOptional = employeeRepo.findById(empId);
        if (employeeRepo.existsById(empId)) {
            Employee employee = employeeOptional.get();
            employeeRepo.delete(employee);
            ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO(employee);
            return responseEmployeeDTO;
        }else{
            throw new EmployeeNotFoundException("Delete: Employee ID " +empId+ "not found");
        }
    }

    @Override
    public ResponseEmployeeDTO createEmployee(RequestEmployeeDTO requestEmployeeDTO) {
        Employee newEmp = new Employee();
        requestEmployeeDTO.toString();
        newEmp.setFirstName(requestEmployeeDTO.getFirstName());
        newEmp.setLastName(requestEmployeeDTO.getLastName());
        newEmp.setDepartment(requestEmployeeDTO.getDepartment());
        newEmp.setDesignation(requestEmployeeDTO.getDesignation());
        newEmp.setSalary(requestEmployeeDTO.getSalary());
        Employee createdEmp = employeeRepo.save(newEmp);
        ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO(createdEmp);
        return responseEmployeeDTO;

    }
}
