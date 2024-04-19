package com.agencia.viajes.service.exceptions;

public class AlojamientoNoEncontradoException extends RuntimeException{
    public AlojamientoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}

