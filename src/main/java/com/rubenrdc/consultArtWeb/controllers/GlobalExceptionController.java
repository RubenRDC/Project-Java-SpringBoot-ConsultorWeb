package com.rubenrdc.consultArtWeb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
}
