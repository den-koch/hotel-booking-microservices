package io.github.denkoch.servicesservice.config.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FutureOrPresentDateValidator.class})
public @interface FutureOrPresentSqlDate {

    String message() default "SQL Date type must be today or in the future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
