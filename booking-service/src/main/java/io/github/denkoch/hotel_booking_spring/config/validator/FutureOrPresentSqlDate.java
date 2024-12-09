package io.github.denkoch.hotel_booking_spring.config.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.FutureOrPresent;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FutureOrPresentDateValidator.class})
public @interface FutureOrPresentSqlDate {

    String message() default "SQL Date type must be today or in the future";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
