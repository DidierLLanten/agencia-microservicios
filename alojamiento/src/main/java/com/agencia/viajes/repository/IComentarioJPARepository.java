package com.agencia.viajes.repository;

import com.agencia.viajes.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IComentarioJPARepository extends JpaRepository<Comentario,Integer> {
    List<Comentario> findComentariosByIdAutor(int idAutor);
}
