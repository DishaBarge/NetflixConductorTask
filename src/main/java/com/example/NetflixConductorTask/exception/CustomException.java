package com.example.NetflixConductorTask.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AtheleteInfoNotFoundException.class)
    public final ResponseEntity<CustomError> handleAtheleteNotFoundException(Exception ex, WebRequest req) {
        CustomError customErrorDetails = new CustomError(ex.getMessage(), req.getDescription(false),LocalDateTime.now());
        return new ResponseEntity<CustomError>(customErrorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        CustomError errorDetails = new CustomError(ex.getFieldError().getDefaultMessage(), req.getDescription(false),
                LocalDateTime.now());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
