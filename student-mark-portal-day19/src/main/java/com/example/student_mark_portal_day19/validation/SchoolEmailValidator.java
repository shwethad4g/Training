package com.example.student_mark_portal_day19.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SchoolEmailValidator implements ConstraintValidator<SchoolEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && email.endsWith("@ves.edu");
    }
}
