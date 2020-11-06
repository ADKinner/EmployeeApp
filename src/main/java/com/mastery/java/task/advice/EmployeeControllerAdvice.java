package com.mastery.java.task.advice;

import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundHandler(EmployeeNotFoundException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamps", LocalDateTime.now());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
