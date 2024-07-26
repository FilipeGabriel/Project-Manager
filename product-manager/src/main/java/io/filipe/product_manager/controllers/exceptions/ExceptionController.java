package io.filipe.product_manager.controllers.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> noSuchElement(NoSuchElementException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Id não encontrado";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<StandardError> propertyValueException(PropertyValueException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        String error = "Os seguintes campos são obrigatórios: " + e.getPropertyName();
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StandardError> invalidFormat(InvalidFormatException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.CONFLICT;
        String error = "Formato do campo invalido, tente novamente!";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.CONFLICT;
        String error = "Preencha todos os campos obrigatórios(*)";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

}
