package com.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidCredential.class)
	public final ResponseEntity<?> handleInvalidCredentialException(InvalidCredential error) {
		return new ResponseEntity<>(error.getLocalizedMessage(), HttpStatus.OK);
	}

}
