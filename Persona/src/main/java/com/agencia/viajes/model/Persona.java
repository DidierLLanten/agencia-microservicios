package com.agencia.viajes.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="\"persona\"")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String cedula;
    private String nombre;
    private String telefono;
    private String ciudad;
    @Column(nullable = false, unique = true)
    private String email;
    private String contraseña;
    private String rol;
}
