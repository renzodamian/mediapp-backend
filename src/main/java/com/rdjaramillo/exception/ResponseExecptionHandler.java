package com.rdjaramillo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
//Interpretar las exceptions que pasan por el proyecto
// (como es un sistema de Rest)
// la anotacion es @RestControllerAdvice
public class ResponseExecptionHandler extends ResponseEntityExceptionHandler {
    //Creamos esta clase para capturar la execpcion

    @ExceptionHandler(Exception.class)
    //Intercepto  el tipo de execption Exception
    public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest request){
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return  new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //Este se puede trabajar en Spring Boot 2 , 1.5
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,HttpStatusCode status, WebRequest request) {
        String msg = ex.getBindingResult().getFieldErrors().stream().map(
                e -> e.getField()
                        .concat(":")
                        .concat(e.getDefaultMessage()
                            .concat(""))
                ).collect(Collectors.joining());

        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), msg, request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }


}

