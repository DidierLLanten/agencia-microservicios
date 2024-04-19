package com.agencia.viajes.repository;

import com.agencia.viajes.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    Optional<Vuelo> findVueloById(int id);
}
