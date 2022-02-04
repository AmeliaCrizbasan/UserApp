package com.example.demo.accessingdatamysql.exception;

public class ConstraintViolationExceptions extends Exception {
    public ConstraintViolationExceptions() {
        super("Email is not valid");
    }
}
