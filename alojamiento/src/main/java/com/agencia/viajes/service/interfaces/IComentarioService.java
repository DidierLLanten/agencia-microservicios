package com.agencia.viajes.service.interfaces;

import com.agencia.viajes.model.Comentario;

import java.util.List;

public interface IComentarioService {
    public Comentario createComentario(Comentario comentario);
    public List<Comentario> getAllComentarios();
    public List<Comentario> getAlojamientosByIdAutor(int idAutor);
}
