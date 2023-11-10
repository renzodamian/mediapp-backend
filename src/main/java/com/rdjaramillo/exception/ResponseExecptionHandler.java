package com.rdjaramillo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
//Interpretar las exceptions que pasan por el proyecto
// (como es un sistema de Rest)
// la anotacion es @RestControllerAdvice
public class ResponseExecptionHandler extends ResponseEntityExceptionHandler {
    //Creamos esta clase para capturar la execpcion


    //Este se peude trabajar en Spring Boot 2 , 1.5
   @ExceptionHandler(ModelNotFoundException.class)
    //Intercepto  el tipo de execption ModelNotFoundException
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Desde Spring Boot 3
    /*@ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Model Not Found Exception");
        problemDetail.setType(URI.create("/not-found"));
        problemDetail.setProperty("extra1","extra-value");
        problemDetail.setProperty("extra2",32);
        return problemDetail;
    }*/

    //Solo Spring Boot 3
    /*@ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Model Not Found Exception")
                .type(URI.create(request.getContextPath()))
                .property("extra1","extra-value")
                .property("extra2",32)
                .build();
    }*/



}

