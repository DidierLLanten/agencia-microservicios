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
    private String cedula;
    private String nombre;
    private String telefono;
    private String ciudad;
    private String email;
    private String contraseña;
    private String rol;
}
