package com.example.employeemanagement.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorInfo {
    private Integer errorCode;

    private String errorMessage;
    private LocalDateTime errorTime;
}
