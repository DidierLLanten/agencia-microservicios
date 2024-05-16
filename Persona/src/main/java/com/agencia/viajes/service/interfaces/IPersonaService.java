package com.agencia.viajes.service.interfaces;

import com.agencia.viajes.model.Persona;

import java.util.List;

public interface IPersonaService {

    public Persona createPersona(Persona persona);
    public Persona updatePersona(int id, Persona persona);
    public void deletePersona(int id);
    public Persona getPersonaById(int id);
    public Persona getPersonaByCedula(String cedula);
    public List<Persona> getAllPersonas();
    public Boolean validarPersonaById(int idPersona);
}
