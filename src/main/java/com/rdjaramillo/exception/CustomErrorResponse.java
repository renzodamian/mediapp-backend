package com.rdjaramillo.exception;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
//Personalizar el msj de Error
public class CustomErrorResponse {

    //Creamos 3 varias y como es una clase simple POJO
    // (Creamos los metodos @Getter @Setter
    //@NoArgsConstructor @AllArgsConstructor)
    //Creamos esta clase que nos va a servir para la
    // forma de contestar el msj de error

    private LocalDateTime dateTime;
    private String message;
    private String details;

}
