package com.agencia.viajes.repository;

import com.agencia.viajes.model.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAlojamientoJPARepository extends JpaRepository<Alojamiento,Integer> {
    List<Alojamiento> findAlojamientosByIdResponsable(int id);
    List<Alojamiento> findAlojamientosByIdDestino(int id);
}
