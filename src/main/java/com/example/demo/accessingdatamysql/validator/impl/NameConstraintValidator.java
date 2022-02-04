package com.example.demo.accessingdatamysql.validator.impl;

import com.example.demo.accessingdatamysql.validator.NameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameConstraintValidator implements ConstraintValidator<NameConstraint, String> {

    @Override
    public void initialize(NameConstraint name) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext ctx) {
        return nameField != null && nameField.matches("[A,a].*");
    }
}
