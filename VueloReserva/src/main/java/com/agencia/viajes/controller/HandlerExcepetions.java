package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExcepetions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Respuesta<String>> capturarDataIntegrityViolationException(DataIntegrityViolationException e){
        if(e.getMessage().contains("foreign key")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respuesta<>("El vuelo se encuentra asociado a una reserva."));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respuesta<>("Error ===> "+e));
    }
}
