package com.example.student_mark_portal_day19.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultipleOfFiveValidator implements ConstraintValidator<MultipleOfFive, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value % 5 == 0;
    }
}
