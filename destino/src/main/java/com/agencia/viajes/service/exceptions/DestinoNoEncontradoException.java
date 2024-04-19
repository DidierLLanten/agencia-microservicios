package com.agencia.viajes.service.exceptions;

public class DestinoNoEncontradoException extends RuntimeException{

    public DestinoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
