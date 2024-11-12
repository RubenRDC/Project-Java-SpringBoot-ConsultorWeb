package com.rubenrdc.consultArtWeb.controllers;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Ruben
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> ErrorValidacionDeObjetoRecibido(/*HttpServletRequest request, Model model, MethodArgumentNotValidException ex*/) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> ErrorJsonMalFormado() {
        return ResponseEntity.badRequest().body(Map.of("error","JSON Mal Formado."));
    }
}
