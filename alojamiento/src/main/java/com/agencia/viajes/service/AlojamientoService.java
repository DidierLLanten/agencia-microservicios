package com.agencia.viajes.service;

import com.agencia.viajes.model.Alojamiento;
import com.agencia.viajes.model.Comentario;
import com.agencia.viajes.repository.IAlojamientoJPARepository;
import com.agencia.viajes.service.exceptions.AlojamientoNoEncontradoException;
import com.agencia.viajes.service.interfaces.IAlojamientoService;
import com.agencia.viajes.service.interfaces.IComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlojamientoService implements IAlojamientoService {

    private final IAlojamientoJPARepository alojamientoJPARepository;
    private final IComentarioService comentarioService;

    @Override
    public List<Alojamiento> getAllAlojamientos() {

        List<Alojamiento> alojamientos = alojamientoJPARepository.findAll();

        // Modificar cada alojamiento para que solo incluya la calificación promedio
        for (Alojamiento alojamiento : alojamientos) {
            double calificacionPromedio = alojamiento.obtenerPromedioCalificacion();
            alojamiento.setCalificaciones(new ArrayList<>()); // Limpiar la lista de calificaciones
            alojamiento.getCalificaciones().add(calificacionPromedio); // Agregar la calificación promedio
        }

        return alojamientos;
    }

    @Override
    public Alojamiento createAlojamiento(Alojamiento alojamiento) {
        return alojamientoJPARepository.save(alojamiento);
    }

    @Override
    public List<Alojamiento> getAlojamientosByIdResponsable(int idResponsable) {
        return alojamientoJPARepository.findAlojamientosByIdResponsable(idResponsable);
    }

    @Override
    public Alojamiento getAlojamientoById(int id) {
        return alojamientoJPARepository.findById(id).orElseThrow( () -> new AlojamientoNoEncontradoException("El alojamiento no existe") );
    }

    @Override
    public Alojamiento updateAlojamiento(int id, Alojamiento alojamiento) {
        Alojamiento currentAlojamiento = getAlojamientoById(id);
        alojamiento.setId(currentAlojamiento.getId());
        return alojamientoJPARepository.save(alojamiento);
    }

    @Override
    public void deleteAlojamiento(int id) {
        getAlojamientoById(id);
        alojamientoJPARepository.deleteById(id);
    }

    @Override
    public List<Alojamiento> getAlojamientosByIdDestino(int idDestino) {
        return alojamientoJPARepository.findAlojamientosByIdDestino(idDestino);
    }

    @Override
    public double calificarAlojamiento(int idAlojamiento, int calificacion) {
        Alojamiento alojamientoACalificar = getAlojamientoById(idAlojamiento);
        alojamientoACalificar.agregarCalificacion(calificacion);
        alojamientoJPARepository.save(alojamientoACalificar);
        return alojamientoACalificar.obtenerPromedioCalificacion();
    }

    @Override
    public void comentarAlojamiento(int idAlojamiento, Comentario comentario) {
        Alojamiento alojamiento = getAlojamientoById(idAlojamiento);
        Comentario comentarioGuardar = comentarioService.createComentario(comentario);
        List<Comentario> comentarios = alojamiento.getComentarios();
        if (comentarios == null) {
            comentarios = new ArrayList<>();
            alojamiento.setComentarios(comentarios);
        }
        comentarios.add(comentarioGuardar);
        alojamientoJPARepository.save(alojamiento);
    }

    @Override
    public Boolean validarAlojamientoById(int idAlojamiento) {
        return alojamientoJPARepository.existsById(idAlojamiento);
    }
}
