package com.agencia.viajes.service.interfaces;

import com.agencia.viajes.model.Reserva;

import java.util.List;

public interface IReservaService {

    public Reserva createReserva(Reserva reserva);
    public Reserva updateReserva(int id,Reserva reserva);
    public void deleteReserva(int id);
    public Reserva getReservaById(int id);
    public List<Reserva> getAllReservas();
    public List<Reserva> getReservasByidPersona(int idPersona);
    public List<Reserva> getReservasByidAlojamiento(int idAlojamiento);
}

