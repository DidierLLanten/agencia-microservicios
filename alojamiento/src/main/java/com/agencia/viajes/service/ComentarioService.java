package com.agencia.viajes.service;

import com.agencia.viajes.config.Constantes;
import com.agencia.viajes.model.Comentario;
import com.agencia.viajes.repository.IComentarioJPARepository;
import com.agencia.viajes.service.interfaces.IComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ComentarioService implements IComentarioService {
    private final IComentarioJPARepository comentarioJPARepository;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public Comentario createComentario(Comentario comentario) {
        validarPersona(comentario.getIdAutor());
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

    private void validarPersona(int idPersona){
        Object respuesta = rabbitTemplate.convertSendAndReceive(Constantes.EXCHANGE, Constantes.ROUTING_KEY, idPersona);

        if(Objects.isNull(respuesta)){
            throw new RuntimeException("Hubo un error recuperando la información de la persona");
        }

        boolean existe = (Boolean) respuesta;
        if(!existe){
            throw new RuntimeException("La persona con id: "+idPersona+" no existe");
        }
    }
}
