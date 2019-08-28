package dev.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;

import dev.exceptions.AnnonceNonTrouveException;
import dev.exceptions.CollegueNonTrouveException;
import dev.exceptions.VoyageCompletException;

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
}


