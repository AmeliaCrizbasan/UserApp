package com.example.demo.accessingdatamysql.exception;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException() {
        super("No country record exist for given id");
    }
}
