package com.agencia.viajes.controller;


import com.agencia.viajes.dto.*;
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

    @PostMapping("/refresh")
    public ResponseEntity<Respuesta<TokenDTO>> refresh(@RequestBody TokenDTO token) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Token actualizado", loginServicio.refresh(token.refreshToken())) );
    }

    @PostMapping("/signup")
    public ResponseEntity<Respuesta<String>> createUser(@RequestBody UserDTO newUserDTO, @RequestHeader("Authorization") String token) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>(loginServicio.createUser(newUserDTO, token) ? "Usuario creado correctamente": "Error", "") );
    }

    @PostMapping("/create-collaborator")
    public ResponseEntity<Respuesta<String>> createEncargado(@RequestBody UserDTO newUserDTO, @RequestHeader("Authorization") String token) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>(loginServicio.createUser(newUserDTO, "app_collaborator",token) ? "Encargado creado correctamente": "Error", "") );
    }

    @GetMapping("/buscar-user/{userName}")
    public ResponseEntity<Respuesta<UserSensibleDTO>> buscarUsuarioById(@RequestHeader("Authorization") String token, @PathVariable("userName") String userName) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Usuario encontrado", loginServicio.searchUser(token, userName)));
    }
}


