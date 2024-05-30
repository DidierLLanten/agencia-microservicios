package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptions {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Respuesta<String>> capturarException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new Respuesta<>(e.getMessage()) );
    }

}
