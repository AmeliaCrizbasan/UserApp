package com.example.demo.accessingdatamysql.exception;

public class CountryNotFoundException extends Exception {
    public CountryNotFoundException() {
        super("Country not found!");
    }
}
