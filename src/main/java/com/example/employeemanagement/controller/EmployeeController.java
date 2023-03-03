package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.RequestEmployeeDTO;
import com.example.employeemanagement.dto.ResponseEmployeeDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.employeemanagement.service.EmployeeService;

import java.util.List;
@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    private static final Log LOGGER = LogFactory.getLog(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "employees")
    public ResponseEntity<List<ResponseEmployeeDTO>> getAllEmployees() {
        LOGGER.info("Testing Logger....");
        List<ResponseEmployeeDTO> responseEmployeeDTOList = employeeService.getAllEmployees();
        if (responseEmployeeDTOList.size() > 0) {
            return new ResponseEntity<List<ResponseEmployeeDTO>>(responseEmployeeDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<ResponseEmployeeDTO>>(responseEmployeeDTOList, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping(value = "employees/{empId}")
    public ResponseEmployeeDTO getEmployeeById(@PathVariable int empId) {
        return employeeService.getEmployeeById(empId);
    }
    @PutMapping("employees/{empId}")
    public ResponseEntity<ResponseEmployeeDTO> updateEmployee(@PathVariable int empId, @RequestBody RequestEmployeeDTO requestEmployeeDTO) {
        ResponseEmployeeDTO responseEmployeeDTO = employeeService.updateEmployee(empId, requestEmployeeDTO);
        if (responseEmployeeDTO.getEmployeeId() ==empId) {
            return new ResponseEntity<ResponseEmployeeDTO>(responseEmployeeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<ResponseEmployeeDTO>(responseEmployeeDTO, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("employees/{empId}")
    public ResponseEntity<ResponseEmployeeDTO> deleteEmployee(@PathVariable int empId) {

        ResponseEmployeeDTO responseEmployeeDTO = employeeService.deleteEmployee(empId);
        if (responseEmployeeDTO != null) {
            return new ResponseEntity<ResponseEmployeeDTO>(responseEmployeeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<ResponseEmployeeDTO>((ResponseEmployeeDTO) null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("employees")
    public ResponseEntity<ResponseEmployeeDTO> createEmployee(@RequestBody RequestEmployeeDTO requestEmployeeDTO) {
        ResponseEmployeeDTO responseEmployeeDTO = employeeService.createEmployee(requestEmployeeDTO);
        if (responseEmployeeDTO != null) {
            return new ResponseEntity<ResponseEmployeeDTO>(responseEmployeeDTO, HttpStatus.CREATED);
        }else {return null;}
    }
}

