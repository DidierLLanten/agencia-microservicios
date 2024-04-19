package com.agencia.viajes.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="\"destino\"")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Region region;
    @Column(nullable = false)
    private Departamento departamento;
    @Column(nullable = false)
    private String lugar;
}
