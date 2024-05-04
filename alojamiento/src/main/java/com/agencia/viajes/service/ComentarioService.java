package com.agencia.viajes.service;

import com.agencia.viajes.model.Comentario;
import com.agencia.viajes.repository.IComentarioJPARepository;
import com.agencia.viajes.service.interfaces.IComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService implements IComentarioService {
    private final IComentarioJPARepository comentarioJPARepository;

    @Override
    public Comentario createComentario(Comentario comentario) {
        return comentarioJPARepository.save(comentario);
    }

    @Override
    public List<Comentario> getAllComentarios() {
        return comentarioJPARepository.findAll();
    }

    @Override
    public List<Comentario> getAlojamientosByIdAutor(int idAutor) {
        return comentarioJPARepository.findComentariosByIdAutor(idAutor);
    }
}
