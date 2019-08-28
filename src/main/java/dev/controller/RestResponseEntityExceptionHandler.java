package dev.controller;


import dev.exception.AnnonceInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;

import dev.exception.AnnonceNonTrouveException;
import dev.exception.CollegueNonTrouveException;
import dev.exception.VoyageCompletException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	
    
    @ExceptionHandler(value = CollegueNonTrouveException.class)
    protected ResponseEntity<Object> handleConflict (CollegueNonTrouveException e, WebRequest req){
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    
    @ExceptionHandler(value = AnnonceNonTrouveException.class)
    protected ResponseEntity<Object> handleConflict (AnnonceNonTrouveException e, WebRequest req){
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    
    @ExceptionHandler(value = VoyageCompletException.class)
    protected ResponseEntity<Object> handleConflict (VoyageCompletException e, WebRequest req){
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = { AnnonceInvalidException.class })
    protected ResponseEntity<Object> handleConflict(AnnonceInvalidException ex) {
        if (ex.getErreurs().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErreurs());
    }
}


