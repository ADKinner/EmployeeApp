package com.mastery.java.task.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundHandler(EmployeeNotFoundException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        Map<String, Object> body = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            body.put(fieldName, message);
        });
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<Object> methodArgumentNotValidHandler(InvalidFormatException exception) {
        Map<String, Object> body = new HashMap<>();
        if (exception.getMessage().contains("LocalDate")) {
            body.put("dateOfBirth", "Such date format is invalid, right format is YYYY-MM-DD");
        }
        if (exception.getMessage().contains("Gender")) {
            body.put("gender", "Employee's gender can be MALE or FEMALE");
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
