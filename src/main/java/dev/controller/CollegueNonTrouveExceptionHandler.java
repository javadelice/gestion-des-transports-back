package dev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dev.exceptions.CollegueNonTrouveException;

@ControllerAdvice
public class CollegueNonTrouveExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CollegueNonTrouveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String collegueInvalideHandler(CollegueNonTrouveException ex) {
      return ex.getMessage();
    }
}
