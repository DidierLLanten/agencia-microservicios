package com.agencia.viajes.service.exceptions;

public class PersonaNoEncontradException extends RuntimeException{

    public PersonaNoEncontradException(String mensaje){
        super(mensaje);
    }
}
