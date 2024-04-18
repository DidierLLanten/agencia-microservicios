package com.agencia.viajes.service;

import com.agencia.viajes.model.Persona;
import com.agencia.viajes.repositorie.IPersonaJPARepository;
import com.agencia.viajes.service.interfaces.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService implements IPersonaService {

    private final IPersonaJPARepository personaJPARepository;


    @Override
    public Persona createPersona(Persona persona) {
        Optional<Persona> personaBuscada = personaJPARepository.findPersonaByCedula(persona.getCedula());
        if(personaBuscada.isPresent()){
            throw new RuntimeException("La persona con la cedula "+persona.getCedula()+" ya existe");
        }
        return personaJPARepository.save(persona);
    }

    @Override
    public Persona updatePersona(int id, Persona persona) {
        return null;
    }

    @Override
    public void deletePersona(int id) {

    }

    @Override
    public Persona getPersonaById(int id) {
        return null;
    }

    @Override
    public Persona getPersonaByCedula(String cedula) {
        return null;
    }

    @Override
    public List<Persona> getAllPersonas() {
        return personaJPARepository.findAll();
    }
}
