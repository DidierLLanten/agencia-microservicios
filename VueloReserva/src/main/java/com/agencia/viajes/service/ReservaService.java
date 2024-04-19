package com.agencia.viajes.service;

import com.agencia.viajes.model.Reserva;
import com.agencia.viajes.repository.ReservaRepository;
import com.agencia.viajes.service.exceptions.ReservaNoEncontradaException;
import com.agencia.viajes.service.interfaces.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;

    @Override
    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva updateReserva(int id,Reserva reserva){
        Reserva currentReserva = getReservaById(id);
        reserva.setId(currentReserva.getId());
        return reservaRepository.save(reserva);
    }

    @Override
    public void deleteReserva(int id) {
        getReservaById(id);
        reservaRepository.deleteById(id);
    }

    @Override
    public Reserva getReservaById(int id) {
        return reservaRepository.findReservaById(id).orElseThrow(()->new ReservaNoEncontradaException("La Reserva no existe"));
    }

    @Override
    public List<Reserva> getAllReservas(){
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> getReservasByidPersona(int idPersona) {
        return reservaRepository.findReservaByidPersona(idPersona);
    }

}
