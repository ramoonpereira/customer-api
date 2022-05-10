package com.ramon.pereira.customerapi.adapter.input.api.handler;

import com.ramon.pereira.customerapi.core.exception.ConflictCustomerException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class CustomControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ConflictCustomerException.class,})
  protected ResponseEntity<Object> handleConflict(
      ConflictCustomerException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
        new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class,})
  protected ResponseEntity<Object> handleNotFound(
      EntityNotFoundException ex, WebRequest request) {
    return handleExceptionInternal(ex, "Resource Not Found",
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

}
