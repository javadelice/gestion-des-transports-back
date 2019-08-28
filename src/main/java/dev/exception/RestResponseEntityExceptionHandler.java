package dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { AnnonceInvalidException.class })
	protected ResponseEntity<Object> handleConflict(AnnonceInvalidException ex) {
		if (ex.getErreurs().isEmpty())
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErreurs());	
	}
}
