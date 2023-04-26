package com.talentica.appusingchatgpt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  // Add more exception handler methods for other exceptions
}
