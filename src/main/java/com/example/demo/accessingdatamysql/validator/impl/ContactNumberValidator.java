package com.example.demo.accessingdatamysql.validator.impl;

import com.example.demo.accessingdatamysql.validator.ContactNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext ctx) {
        return contactField != null && contactField.matches("[0-9]+");
    }

}