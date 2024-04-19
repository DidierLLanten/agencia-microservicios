package com.agencia.viajes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="\"alojamiento\"")
public class Alojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idResponsable;
    private String nombre;
    private int idDestino;
    private String ubicacion;
    private int calificacion;
    private String caracteristicas;
    private int capacidad;
    private int precio;

    @ElementCollection
    @CollectionTable(name="fotos", joinColumns=@JoinColumn(name="alojamiento_id"))
    @Column(name="foto")
    private List<String> fotos;

    @ElementCollection
    @CollectionTable(name="fotos", joinColumns=@JoinColumn(name="alojamiento_id"))
    @Column(name="foto")
    private List<String> comentarios;
}
