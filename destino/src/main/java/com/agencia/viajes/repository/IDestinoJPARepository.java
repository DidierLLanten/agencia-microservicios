package com.agencia.viajes.repository;

import com.agencia.viajes.model.Departamento;
import com.agencia.viajes.model.Destino;
import com.agencia.viajes.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDestinoJPARepository extends JpaRepository<Destino,Integer> {
    Optional<Destino> findDestinoByRegion(Region region);
    Optional<Destino> findDestinoByDepartamento(Departamento departamento);
    Optional<Destino> findDestinoByLugar(String lugar);
}
