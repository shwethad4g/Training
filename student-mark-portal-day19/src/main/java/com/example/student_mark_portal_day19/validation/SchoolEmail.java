package com.example.student_mark_portal_day19.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SchoolEmailValidator.class)
public @interface SchoolEmail {
    String message() default "Email must end with @ves.edu";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
