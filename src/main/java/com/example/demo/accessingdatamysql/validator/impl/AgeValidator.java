package com.example.demo.accessingdatamysql.validator.impl;

import com.example.demo.accessingdatamysql.validator.AgeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<AgeConstraint, Integer> {

    @Override
    public void initialize(AgeConstraint age) {
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext cxt) {
        return age != null && (age > 18 && age < 100);
    }

}