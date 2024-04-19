package com.agencia.viajes.service.interfaces;

import com.agencia.viajes.model.Vuelo;

import java.util.List;

public interface IVueloService {

    public Vuelo createVuelo(Vuelo vuelo);
    public Vuelo updateVuelo(int id, Vuelo vuelo);
    public void deleteVuelo(int id);
    public Vuelo getVueloById(int id);
    public List<Vuelo> getAllVuelos();
}
