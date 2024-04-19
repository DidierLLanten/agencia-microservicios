package com.agencia.viajes.controller;

import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.model.Persona;
import com.agencia.viajes.service.interfaces.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
@RequiredArgsConstructor
public class PersonaController {
    private final IPersonaService personaService;

    @PostMapping
    public ResponseEntity<Respuesta<Persona>> save(@RequestBody Persona persona){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Persona creada correctamente", personaService.createPersona(persona)) );
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<Persona>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", personaService.getAllPersonas()) );
    }

    @GetMapping("/{idPersona}")
    public ResponseEntity<Respuesta<Persona>> findById(@PathVariable int idPersona){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", personaService.getPersonaById(idPersona)) );
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<Respuesta<Persona>> findById(@PathVariable String cedula){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", personaService.getPersonaByCedula(cedula)) );
    }

    @PutMapping("/{idPersona}")
    public ResponseEntity<Respuesta<Persona>> update(@PathVariable int idPersona, @RequestBody Persona persona){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("La persona se modificó correctamente", personaService.updatePersona(idPersona, persona)));
    }

    @DeleteMapping("/{idPersona}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable int idPersona){
        personaService.deletePersona(idPersona);
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Se eliminó correctamente") );
    }
}
