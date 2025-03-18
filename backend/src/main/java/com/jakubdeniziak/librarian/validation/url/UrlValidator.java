package com.jakubdeniziak.librarian.validation.url;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

    private final org.apache.commons.validator.routines.UrlValidator urlValidator =
            new org.apache.commons.validator.routines.UrlValidator(new String[]{"http", "https"});

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return urlValidator.isValid(value);
    }
}