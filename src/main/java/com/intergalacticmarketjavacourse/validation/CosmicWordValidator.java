package com.intergalacticmarketjavacourse.validation;

import com.intergalacticmarketjavacourse.domain.ProductName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return ProductName.containsCosmicTerm(value);
    }
}