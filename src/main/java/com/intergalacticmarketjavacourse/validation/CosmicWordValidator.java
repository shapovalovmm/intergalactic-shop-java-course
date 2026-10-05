package com.intergalacticmarketjavacourse.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class CosmicWordValidator
        implements ConstraintValidator<CosmicWordCheck, String> {

    private static final Set<String> COSMIC_TERMS = Set.of(
            "star",
            "galaxy",
            "comet"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String normalizedValue = value.toLowerCase();

        return COSMIC_TERMS.stream()
                .anyMatch(normalizedValue::contains);
    }
}