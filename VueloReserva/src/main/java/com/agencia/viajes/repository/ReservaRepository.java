package com.agencia.viajes.repository;

import com.agencia.viajes.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    Optional<Reserva> findReservaById(int id);

    List<Reserva>findReservaByidPersona(int idPersona);
}
