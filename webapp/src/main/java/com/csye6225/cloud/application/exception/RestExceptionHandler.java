package com.csye6225.cloud.application.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    HttpHeaders httpHeaders = new HttpHeaders();
    RestExceptionHandler() {
        httpHeaders.setPragma("no-cache");
        httpHeaders.add("X-Content-Type-Options", "nosniff");
        httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exc) {
        ExceptionResponse error = new ExceptionResponse(exc.getMessage());
        logger.error("Exception occurred: ", exc);
        return ResponseEntity.status(400).headers(httpHeaders).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(BadRequestException exc) {
        ExceptionResponse error = new ExceptionResponse(exc.getMessage());
        logger.error("BadRequestException occurred: ", exc);
        return ResponseEntity.status(400).headers(httpHeaders).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(NoSuchFieldException exc) {
        ExceptionResponse error = new ExceptionResponse(exc.getMessage());
        logger.error("NoSuchFieldException occurred: ", exc);
        return ResponseEntity.status(400).headers(httpHeaders).body(error);
    }

}
