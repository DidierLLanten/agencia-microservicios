package com.agencia.viajes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="\"vuelo\"")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String aerolinea;
    private String ciudadOrigen;
    private String ciudadDestino;

    private Date fechaIda;

    private Date fechaVuelta;

    private double precio;
}
