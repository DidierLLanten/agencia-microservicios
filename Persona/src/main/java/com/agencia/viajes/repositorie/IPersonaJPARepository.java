package com.agencia.viajes.repositorie;

import com.agencia.viajes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonaJPARepository extends JpaRepository<Persona,Integer> {
    Optional<Persona> findPersonaByCedula(String cedula);
}
