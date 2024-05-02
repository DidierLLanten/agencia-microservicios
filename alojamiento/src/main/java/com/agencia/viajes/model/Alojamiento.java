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
    // private int calificacion;
    private String caracteristicas;
    private int capacidad;
    private int precio;

    @ElementCollection
    @CollectionTable(name="fotos", joinColumns=@JoinColumn(name="alojamiento_id"))
    @Column(name="foto")
    private List<String> fotos;

    @ElementCollection
    @CollectionTable(name="comentarios", joinColumns=@JoinColumn(name="alojamiento_id"))
    @Column(name="comentario")
    private List<String> comentarios;

    @ElementCollection
    @CollectionTable(name="calificaciones", joinColumns=@JoinColumn(name="alojamiento_id"))
    @Column(name="calificacion")
    private List<Integer> calificaciones = new ArrayList<>();

    public double obtenerPromedioCalificacion() {
        // Verificar que haya al menos una calificación
        if (calificaciones.isEmpty()) {
            return 0;
        }

        // Calcular la suma de todas las calificaciones
        int sumaCalificaciones = calificaciones.stream()
                .mapToInt(Integer::intValue)
                .sum();

        // Calcular el promedio
        return (double) sumaCalificaciones / calificaciones.size();
    }

    public void agregarCalificacion(int calificacion) {
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
