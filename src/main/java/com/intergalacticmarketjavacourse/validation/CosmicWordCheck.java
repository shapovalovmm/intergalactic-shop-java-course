package com.intergalacticmarketjavacourse.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CosmicWordValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface CosmicWordCheck {

    String message() default "Product name must contain a cosmic term: star, galaxy, comet...";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}