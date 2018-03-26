package com.eric.monitoringserverjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // Catch all for now
    @ExceptionHandler(Exception.class)
    ResponseEntity exceptionHandler (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity accessDeniedExceptionHandler (Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                             .build();
    }
}
