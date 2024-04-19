package com.agencia.viajes.repository;

import com.agencia.viajes.model.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAlojamientoJPARepository extends JpaRepository<Alojamiento,Integer> {
    Optional<Alojamiento> findAlojamientoByIdResponsable(int id);
    Optional<Alojamiento> findAlojamientoByIdDestino(int id);
}
