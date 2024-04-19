package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.model.Alojamiento;
import com.agencia.viajes.service.interfaces.IAlojamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alojamiento")
@RequiredArgsConstructor
public class AlojamientoController {
    private final IAlojamientoService alojamientoService;

    @GetMapping
    public ResponseEntity<Respuesta<List<Alojamiento>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", alojamientoService.getAllAlojamientos()) );
    }
    
    @PostMapping
    public ResponseEntity<Respuesta<Alojamiento>> save(@RequestBody Alojamiento alojamiento){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Alojamiento creada correctamente", alojamientoService.createAlojamiento(alojamiento)) );
    }

    @GetMapping("/responsable/{idResponsable}")
    public ResponseEntity<Respuesta<Optional<Alojamiento>>> findByIdResponsable(@PathVariable int idResponsable){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", alojamientoService.getAlojamientoByIdResponsable(idResponsable)) );
    }

    @GetMapping("/{idAlojamiento}")
    public ResponseEntity<Respuesta<Alojamiento>> findById(@PathVariable int idAlojamiento){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", alojamientoService.getAlojamientoById(idAlojamiento)) );
    }

    @PutMapping("/{idAlojamiento}")
    public ResponseEntity<Respuesta<Alojamiento>> update(@PathVariable int idAlojamiento, @RequestBody Alojamiento alojamiento){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("La alojamiento se modificó correctamente", alojamientoService.updateAlojamiento(idAlojamiento, alojamiento)));
    }

    @DeleteMapping("/{idAlojamiento}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable int idAlojamiento){
        alojamientoService.deleteAlojamiento(idAlojamiento);
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Se eliminó correctamente") );
    }

    @GetMapping("/destino/{idDestino}")
    public ResponseEntity<Respuesta<Optional<Alojamiento>>> findByIdDestino(@PathVariable int idDestino){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", alojamientoService.getAlojamientoByIdDestino(idDestino)) );
    }

    @PutMapping("/calificar/{idAlojamiento}")
    public ResponseEntity<Respuesta<Alojamiento>> calificar(@PathVariable int idAlojamiento, @RequestBody int calificacion){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Alojamiento calificado", alojamientoService.calificarAlojamiento(idAlojamiento, calificacion)));
    }
}


