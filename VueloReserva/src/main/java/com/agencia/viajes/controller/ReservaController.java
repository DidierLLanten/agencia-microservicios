package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.model.Reserva;
import com.agencia.viajes.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserva")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<Respuesta<List<Reserva>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",reservaService.getAllReservas()));
    }

    @PostMapping
    public ResponseEntity<Respuesta<Reserva>> save(@RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Reserva creada correctamente",reservaService.createReserva(reserva)));
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<Respuesta<Reserva>>fingById(@PathVariable int idReserva) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",reservaService.getReservaById(idReserva)));
    }

    @PutMapping("/{idReserva}")
    public ResponseEntity<Respuesta<Reserva>> update(@PathVariable int idReserva, @RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("La reserva se actualizo correctamente",reservaService.updateReserva(idReserva,reserva)));
    }

    @DeleteMapping("/{idReserva}")
    public ResponseEntity<Respuesta<Reserva>> delete(@PathVariable int idReserva) {
        reservaService.deleteReserva(idReserva);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Se elimino la reserva correctamente"));
    }

    @GetMapping("/responsable/{idResponsable}")
    public ResponseEntity<Respuesta<List<Reserva>>> findReservasByResponsable(@PathVariable int idResponsable) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("",reservaService.getReservasByidPersona(idResponsable)));
    }
}
