package com.agencia.viajes.controller;


import com.agencia.viajes.dto.LoginDTO;
import com.agencia.viajes.dto.Respuesta;
import com.agencia.viajes.dto.TokenDTO;
import com.agencia.viajes.service.LoginServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {
    private final LoginServicio loginServicio;

    @PostMapping("/signin")
    public ResponseEntity<Respuesta<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Login correcto", loginServicio.login(loginDTO)) );
    }

    /*@PostMapping("/refresh")
    public ResponseEntity<Respuesta<TokenDTO>> refresh(@RequestBody TokenDTO token) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", loginServicio.refresh(token)) );
    }*/

    /*@PostMapping("/signup")
    public ResponseEntity<Respuesta<String>> createUser(@RequestBody NewUserDTO newUserDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>(loginServicio.createUser(newUserDTO) ? "Creado correctamente": "Error", "") );
    }*/
}


