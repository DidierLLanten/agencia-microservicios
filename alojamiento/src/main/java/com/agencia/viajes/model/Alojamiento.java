package com.agencia.viajes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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
    private String caracteristicas;
    private int capacidad;
    private int precio;

    @ElementCollection
    @CollectionTable(name="fotos", joinColumns=@JoinColumn(name="alojamiento_id"))
    @Column(name="foto")
    private List<String> fotos;

    @OneToMany()
    @JoinColumn(name = "id_comentario")
    private List<Comentario> comentarios;

    @ElementCollection
    @CollectionTable(name="calificaciones", joinColumns=@JoinColumn(name="alojamiento_id"))
    @Column(name="calificacion")
    private List<Double> calificaciones = new ArrayList<>();

    public double obtenerPromedioCalificacion() {
        if (calificaciones.isEmpty()) {
            return 0;
        }

        // Calcular la suma de todas las calificaciones
        double sumaCalificaciones = calificaciones.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        // Calcular el promedio
        return sumaCalificaciones / calificaciones.size();
    }

    public void agregarCalificacion(double calificacion) {
        calificaciones.add(calificacion);
    }

    @Override
    public String toString() {
        return "Alojamiento{" +
                "id=" + id +
                ", idResponsable=" + idResponsable +
                ", nombre='" + nombre + '\'' +
                ", idDestino=" + idDestino +
                ", ubicacion='" + ubicacion + '\'' +
                ", caracteristicas='" + caracteristicas + '\'' +
                ", capacidad=" + capacidad +
                ", precio=" + precio +
                ", calificacionPromedio=" + obtenerPromedioCalificacion() +
                '}';
    }
}
