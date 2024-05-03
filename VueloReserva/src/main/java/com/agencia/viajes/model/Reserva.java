package com.agencia.viajes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="\"reserva\"")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idPersona;

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    private int idAlojamiento;
    private int duracion;
    private Date fechaReserva;
    private int ocupantes;
}
