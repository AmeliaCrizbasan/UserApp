package com.example.demo.accessingdatamysql.validator;

import com.example.demo.accessingdatamysql.validator.impl.AgeValidator;
import com.example.demo.accessingdatamysql.validator.impl.ContactNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)


public @interface AgeConstraint {
    String message() default "Invalid age";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
