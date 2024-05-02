package com.agencia.viajes.service.interfaces;

import com.agencia.viajes.model.Alojamiento;

import java.util.List;
import java.util.Optional;

public interface IAlojamientoService {
    public List<Alojamiento> getAllAlojamientos();
    public Alojamiento createAlojamiento(Alojamiento alojamiento);
    public List<Alojamiento> getAlojamientosByIdResponsable(int idResponsable);
    public Alojamiento getAlojamientoById(int id);
    public Alojamiento updateAlojamiento(int id, Alojamiento alojamiento);
    public void deleteAlojamiento(int id);
    public List<Alojamiento> getAlojamientosByIdDestino(int idDestino);
    public double calificarAlojamiento(int idAlojamiento, int calificacion);
    public void comentarAlojamiento(int idAlojamiento, String comentario);
}
