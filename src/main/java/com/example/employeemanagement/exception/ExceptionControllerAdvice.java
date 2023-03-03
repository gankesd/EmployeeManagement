package com.example.employeemanagement.exception;


import jakarta.validation.ConstraintViolationException;
import org.hibernate.cfg.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    Environment environment;
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorInfo> employeeNotFoundException(EmployeeNotFoundException exception){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorInfo.setErrorTime(LocalDateTime.now());
        errorInfo.setErrorMessage(exception.getMessage());
        return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(ConstraintViolationException exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setErrorTime(LocalDateTime.now());
        String errorMsg = exception.getConstraintViolations().stream().map((cv)->cv.getMessage()).collect(Collectors.joining(","));
        errorInfo.setErrorMessage(errorMsg);

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
