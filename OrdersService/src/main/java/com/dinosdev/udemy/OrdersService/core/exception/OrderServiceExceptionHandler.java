package com.dinosdev.udemy.OrdersService.core.exception;

import java.util.Date;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class OrderServiceExceptionHandler {
  @ExceptionHandler(value = {IllegalStateException.class})
  public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ise, WebRequest request) {
    ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), ise.getMessage());
    return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {CommandExecutionException.class})
  public ResponseEntity<Object> handleCommandExecutionException(CommandExecutionException cee, WebRequest request) {
    ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), cee.getMessage());
    return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleOtherExceptions(Exception e, WebRequest request) {
    ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), e.getMessage());
    return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
