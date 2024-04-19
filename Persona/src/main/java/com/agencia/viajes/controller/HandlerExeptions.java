package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.service.exceptions.PersonaNoEncontradException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class HandlerExeptions {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Respuesta<String>> capturarException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new Respuesta<>(e.getMessage()+"°***********°" +e) );
    }

    @ExceptionHandler(PersonaNoEncontradException.class)
    public ResponseEntity<Respuesta<String>> capturarClienteNoEncontradoException(PersonaNoEncontradException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Respuesta<>(e.getMessage()) );
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Respuesta<String>> capturarDataIntegrityViolationException(DataIntegrityViolationException e){
        String mensaje = e.getRootCause().getMessage(); // Obtener el mensaje de la causa raízraíz
        String datoDuplicado = obtenerDatoDuplicado(mensaje);
        if (datoDuplicado != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respuesta<>("Este dato "+ datoDuplicado + " ya se encuentra registrado en otra cuenta: " ));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Respuesta<>(mensaje));
    }

    private String obtenerDatoDuplicado(String mensaje) {
        String[] partesMensaje = mensaje.split("'");
        if (partesMensaje.length > 1) {
            return partesMensaje[1];
        }
        return null;
    }

    private String obtenerEmailDuplicado(String mensaje) {
        String[] partesMensaje = mensaje.split("'");
        for (int i = 0; i < partesMensaje.length; i++) {
            System.out.println("******************************"+partesMensaje[i]);
            if (partesMensaje[i].equals("Duplicate entry ") && i < partesMensaje.length - 1) {
                // Si la parte actual es "Duplicate entry " y hay una parte siguiente
                // la dirección de correo electrónico duplicada es la siguiente parte
                return partesMensaje[i + 1];
            }
        }
        return null; // Si no se encuentra el correo electrónico duplicado en el mensaje
    }

}
