package com.nab.api.product.exception;

import com.nab.api.product.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOtherException(Exception ex){
        LOGGER.error(ex.getMessage());
        return handleException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex) {
        LOGGER.error(ex.getMessage());
        return handleException(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleValidationException(NotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return handleException(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleException(String error, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setTimestamp(OffsetDateTime.now());
        errorResponse.setErrorMessage(error);
        return new ResponseEntity<>(errorResponse, status);
    }
}
