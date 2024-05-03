package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.dto.RutaVuelo;
import com.agencia.viajes.model.Reserva;
import com.agencia.viajes.model.Vuelo;
import com.agencia.viajes.service.VueloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vuelo")
@RequiredArgsConstructor
public class VueloController {

    private final VueloService vueloService;

    @GetMapping
    public ResponseEntity<Respuesta<List<Vuelo>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",vueloService.getAllVuelos()));
    }

    @PostMapping
    public ResponseEntity<Respuesta<Vuelo>> save(@RequestBody Vuelo vuelo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Vuelo creado correctamente",vueloService.createVuelo(vuelo)));
    }

    @GetMapping("/{idVuelo}")
    public ResponseEntity<Respuesta<Vuelo>>findById(@PathVariable int idVuelo) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",vueloService.getVueloById(idVuelo)));
    }

    @PutMapping("/{idVuelo}")
    public ResponseEntity<Respuesta<Vuelo>> update(@PathVariable int idVuelo, @RequestBody Vuelo vuelo) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El vuelo se actualizó correctamente",vueloService.updateVuelo(idVuelo, vuelo)));
    }

    @DeleteMapping("/{idVuelo}")
    public ResponseEntity<Respuesta<Vuelo>> delete(@PathVariable int idVuelo) {
        vueloService.deleteVuelo(idVuelo);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Se eliminó el vuelo correctamente"));
    }

    @GetMapping("/ciudadOrigen/{ciudadOrigen}")
    public ResponseEntity<Respuesta<List<Vuelo>>> findVuelosByCiudadOrigen(@PathVariable String ciudadOrigen) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",vueloService.getVuelosByOrigen(ciudadOrigen)));
    }

    @GetMapping("/ciudadDestino/{ciudadDestino}")
    public ResponseEntity<Respuesta<List<Vuelo>>> findVuelosByCiudadDestino(@PathVariable String ciudadDestino) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",vueloService.getVuelosByDestino(ciudadDestino)));
    }

    @GetMapping("/ruta")
    public ResponseEntity<Respuesta<List<Vuelo>>> findVuelosByRutaVuelo(@RequestBody RutaVuelo rutaVuelo) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",vueloService.getVuelosByOrigenAndDestino(rutaVuelo)));
    }
}
