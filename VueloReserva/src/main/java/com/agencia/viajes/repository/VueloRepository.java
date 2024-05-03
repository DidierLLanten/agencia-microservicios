package com.agencia.viajes.repository;

import com.agencia.viajes.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    List<Vuelo> findByCiudadOrigen(String ciudadOrigen);
    List<Vuelo> findByCiudadDestino(String ciudadOrigen);
    List<Vuelo> findByCiudadOrigenAndCiudadDestino(String ciudadOrigen, String ciudadDestino);
}
