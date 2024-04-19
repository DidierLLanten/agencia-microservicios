package com.agencia.viajes.service.interfaces;

import com.agencia.viajes.model.Alojamiento;

import java.util.List;
import java.util.Optional;

public interface IAlojamientoService {
    public List<Alojamiento> getAllAlojamientos();
    public Alojamiento createAlojamiento(Alojamiento alojamiento);
    public Optional<Alojamiento> getAlojamientoByIdResponsable(int idResponsable);
    public Alojamiento getAlojamientoById(int id);
    public Alojamiento updateAlojamiento(int id, Alojamiento alojamiento);
    public void deleteAlojamiento(int id);
    public Optional<Alojamiento> getAlojamientoByIdDestino(int idDestino);
    public Alojamiento calificarAlojamiento(int idAlojamiento, int calificacion);
}
