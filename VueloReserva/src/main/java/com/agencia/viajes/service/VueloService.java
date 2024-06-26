package com.agencia.viajes.service;

import com.agencia.viajes.dto.RutaVuelo;
import com.agencia.viajes.model.Vuelo;
import com.agencia.viajes.repository.VueloRepository;
import com.agencia.viajes.service.exceptions.VueloNoEncontradoException;
import com.agencia.viajes.service.interfaces.IVueloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VueloService implements IVueloService {

    private final VueloRepository vueloRepository;

    @Override
    public Vuelo createVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Vuelo updateVuelo(int id, Vuelo vuelo) {
        Vuelo currentVuelo = getVueloById(id);
        vuelo.setId(currentVuelo.getId());
        return vueloRepository.save(vuelo);
    }

    @Override
    public void deleteVuelo(int id) {
        getVueloById(id);
        vueloRepository.deleteById(id);
    }

    @Override
    public Vuelo getVueloById(int id) {
        return vueloRepository.findById(id).orElseThrow(()->new VueloNoEncontradoException("El vuelo no existe"));
    }

    @Override
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public List<Vuelo> getVuelosByOrigen(String ciudadOrigen) {
        return vueloRepository.findByCiudadOrigen(ciudadOrigen);
    }

    @Override
    public List<Vuelo> getVuelosByDestino(String ciudadDestino) {
        return vueloRepository.findByCiudadDestino(ciudadDestino);
    }

    @Override
    public List<Vuelo> getVuelosByOrigenAndDestino(RutaVuelo rutaVuelo) {
        return vueloRepository.findByCiudadOrigenAndCiudadDestino(rutaVuelo.getCiudadOrigen(), rutaVuelo.getCiudadDestino());
    }
}
