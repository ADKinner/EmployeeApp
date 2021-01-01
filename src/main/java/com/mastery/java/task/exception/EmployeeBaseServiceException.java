package com.mastery.java.task.exception;

public class EmployeeBaseServiceException extends RuntimeException {

    public EmployeeBaseServiceException(String message) {
        super(message);
    }

    public EmployeeBaseServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeBaseServiceException(Throwable cause) {
        super(cause);
    }
}
