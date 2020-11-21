package com.mastery.java.task.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundHandler(EmployeeNotFoundException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
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

    @ResponseBody
    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<Object> methodArgumentNotValidHandler(InvalidFormatException exception) {
        Map<String, Object> body = new HashMap<>();
        if (exception.getMessage().contains("LocalDate")) {
            body.put("dateOfBirth", "Such date format is invalid");
        } else if (exception.getMessage().contains("Gender")) {
            body.put("gender", "Employee's gender can be MALE or FEMALE");
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
