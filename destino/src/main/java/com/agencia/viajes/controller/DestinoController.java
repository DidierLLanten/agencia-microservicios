package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.model.Departamento;
import com.agencia.viajes.model.Destino;
import com.agencia.viajes.model.Region;
import com.agencia.viajes.service.interfaces.IDestinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destino")
@RequiredArgsConstructor
public class DestinoController {
    private final IDestinoService destinoService;

    @PostMapping
    public ResponseEntity<Respuesta<Destino>> save(@RequestBody Destino destino){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Destino creada correctamente", destinoService.createDestino(destino)) );
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<Destino>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", destinoService.getAllDestinos()) );
    }

    @GetMapping("/{idDestino}")
    public ResponseEntity<Respuesta<Destino>> findById(@PathVariable int idDestino){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", destinoService.getDestinoById(idDestino)) );
    }

    @PutMapping("/{idDestino}")
    public ResponseEntity<Respuesta<Destino>> update(@PathVariable int idDestino, @RequestBody Destino destino){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("La destino se modificó correctamente", destinoService.updateDestino(idDestino, destino)));
    }

    @DeleteMapping("/{idDestino}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable int idDestino){
        destinoService.deleteDestino(idDestino);
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Se eliminó correctamente") );
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<Respuesta<Destino>> findByRegion(@PathVariable Region region){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", destinoService.getDestinoByRegion(region)) );
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<Respuesta<Destino>> findByDepartamento(@PathVariable Departamento departamento){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", destinoService.getDestinoByDepartamento(departamento)) );
    }

    @GetMapping("/lugar/{lugar}")
    public ResponseEntity<Respuesta<Destino>> findByLugar(@PathVariable String lugar){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", destinoService.getDestinoByLugar(lugar)) );
    }
}
