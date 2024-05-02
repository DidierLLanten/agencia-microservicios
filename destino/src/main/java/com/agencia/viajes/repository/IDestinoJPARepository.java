package com.agencia.viajes.repository;

import com.agencia.viajes.model.Departamento;
import com.agencia.viajes.model.Destino;
import com.agencia.viajes.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDestinoJPARepository extends JpaRepository<Destino,Integer> {
    List<Destino> findDestinosByRegion(Region region);
    List<Destino> findDestinosByDepartamento(Departamento departamento);
    List<Destino> findDestinosByLugar(String lugar);
}
