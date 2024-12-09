package io.github.denkoch.hotel_booking_spring.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleException(ResourceNotFoundException exception) {
        return ErrorResponse.create(exception, HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ErrorResponse handleException(ResourceAlreadyExistsException exception) {
        return ErrorResponse.create(exception, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleException(DataIntegrityViolationException exception) {
        return ErrorResponse.create(exception, HttpStatus.CONFLICT, "A unique constraint violation occurred");
    }

}
