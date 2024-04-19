package com.agencia.viajes.service.interfaces;

import com.agencia.viajes.model.Departamento;
import com.agencia.viajes.model.Destino;
import com.agencia.viajes.model.Region;

import java.util.List;

public interface IDestinoService {
    public Destino createDestino(Destino destino);
    public Destino updateDestino(int id, Destino destino);
    public void deleteDestino(int id);
    public Destino getDestinoById(int id);
    public List<Destino> getAllDestinos();
    public Destino getDestinoByRegion(Region region);
    public Destino getDestinoByDepartamento(Departamento departamento);
    public Destino getDestinoByLugar(String lugar);
}
