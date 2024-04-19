package com.agencia.viajes.service;

import com.agencia.viajes.model.Alojamiento;
import com.agencia.viajes.repository.IAlojamientoJPARepository;
import com.agencia.viajes.service.exceptions.AlojamientoNoEncontradoException;
import com.agencia.viajes.service.interfaces.IAlojamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlojamientoService implements IAlojamientoService {

    private final IAlojamientoJPARepository alojamientoJPARepository;

    @Override
    public List<Alojamiento> getAllAlojamientos() {
        return alojamientoJPARepository.findAll();
    }

    @Override
    public Alojamiento createAlojamiento(Alojamiento alojamiento) {
        return alojamientoJPARepository.save(alojamiento);
    }

    @Override
    public Optional<Alojamiento> getAlojamientoByIdResponsable(int idResponsable) {
        return alojamientoJPARepository.findAlojamientoByIdResponsable(idResponsable);
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
    public Optional<Alojamiento> getAlojamientoByIdDestino(int idDestino) {
        return alojamientoJPARepository.findAlojamientoByIdDestino(idDestino);
    }

    @Override
    public Alojamiento calificarAlojamiento(int idAlojamiento, int calificacion) {
        Alojamiento currentAlojamiento = getAlojamientoById(idAlojamiento);
        currentAlojamiento.setCalificacion(calificacion);
        return alojamientoJPARepository.save(currentAlojamiento);
    }
}
