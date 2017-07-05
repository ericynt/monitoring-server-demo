package com.eric.monitoringserverjava.exceoptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	ResponseEntity exceptionHandler (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
