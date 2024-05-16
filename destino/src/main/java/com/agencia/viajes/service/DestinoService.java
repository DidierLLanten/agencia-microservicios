package com.agencia.viajes.service;

import com.agencia.viajes.model.Departamento;
import com.agencia.viajes.model.Destino;
import com.agencia.viajes.model.Region;
import com.agencia.viajes.repository.IDestinoJPARepository;
import com.agencia.viajes.service.exceptions.DestinoNoEncontradoException;
import com.agencia.viajes.service.interfaces.IDestinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinoService implements IDestinoService {

    private final IDestinoJPARepository destinoJPARepository;
    
    @Override
    public Destino createDestino(Destino destino) {
        return destinoJPARepository.save(destino);
    }

    @Override
    public Destino updateDestino(int id, Destino destino) {
        Destino currentCliente = getDestinoById(id);
        destino.setId(currentCliente.getId());
        return destinoJPARepository.save(destino);
    }

    @Override
    public void deleteDestino(int id) {
        getDestinoById(id);
        destinoJPARepository.deleteById(id);
    }

    @Override
    public Destino getDestinoById(int id) {
        return destinoJPARepository.findById(id).orElseThrow( () -> new DestinoNoEncontradoException("El destino no existe") );
    }

    @Override
    public List<Destino> getAllDestinos() {
        return destinoJPARepository.findAll();
    }

    @Override
    public List<Destino> getDestinosByRegion(Region region) {
        return destinoJPARepository.findDestinosByRegion(region);
    }

    @Override
    public List<Destino> getDestinosByDepartamento(Departamento departamento) {
        return destinoJPARepository.findDestinosByDepartamento(departamento);
    }

    @Override
    public List<Destino> getDestinosByLugar(String lugar) {
        return destinoJPARepository.findDestinosByLugar(lugar);
    }

    @Override
    public Boolean validarDestinoById(int idPersona) {
        return destinoJPARepository.existsById(idPersona);
    }
}
