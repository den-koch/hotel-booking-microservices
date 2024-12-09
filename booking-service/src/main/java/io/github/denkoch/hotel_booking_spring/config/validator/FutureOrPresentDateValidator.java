package io.github.denkoch.hotel_booking_spring.config.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.FutureOrPresent;

import java.sql.Date;
import java.time.LocalDate;

public class FutureOrPresentDateValidator implements ConstraintValidator<FutureOrPresentSqlDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }
        LocalDate today = LocalDate.now();
        LocalDate providedDate = date.toLocalDate();
        return !providedDate.isBefore(today);
    }
}
