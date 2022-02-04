package com.example.demo.accessingdatamysql.validator;


import com.example.demo.accessingdatamysql.validator.impl.NameConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface NameConstraint {
    String message() default "Invalid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
