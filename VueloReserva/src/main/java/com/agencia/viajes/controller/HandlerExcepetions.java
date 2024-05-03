package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.service.exceptions.ReservaNoEncontradaException;
import com.agencia.viajes.service.exceptions.VueloNoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExcepetions {
    @ExceptionHandler(VueloNoEncontradoException.class)
    public ResponseEntity<Respuesta<String>> capturarVueloNoEncontradoException(VueloNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Respuesta<>(e.getMessage()) );
    }

    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<Respuesta<String>> capturarReservaNoEncontradaException(ReservaNoEncontradaException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Respuesta<>(e.getMessage()) );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Respuesta<String>> capturarDataIntegrityViolationException(DataIntegrityViolationException e){
        if(e.getMessage().contains("Cannot add or update a child row: a foreign key constraint fails")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respuesta<>("El vuelo especificado no existe."));
        }
        if(e.getMessage().contains("foreign key")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respuesta<>("El vuelo se encuentra asociado a una reserva."));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respuesta<>("Error ===> "+e));
    }
}
