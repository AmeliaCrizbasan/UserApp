package com.example.demo.accessingdatamysql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CountryNotFoundException.class, RecordNotFoundException.class})
    protected ResponseEntity<ApiError> handleNotFoundError(Exception ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<ApiError> handleBadRequest(Exception ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }
}
