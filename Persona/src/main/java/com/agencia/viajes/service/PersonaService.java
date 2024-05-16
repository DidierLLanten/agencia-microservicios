package com.agencia.viajes.service;

import com.agencia.viajes.model.Persona;
import com.agencia.viajes.repositorie.IPersonaJPARepository;
import com.agencia.viajes.service.exceptions.PersonaNoEncontradException;
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
        return personaJPARepository.save(persona);
    }

    @Override
    public Persona updatePersona(int id, Persona persona) {
        Persona currentCliente = getPersonaById(id);
        persona.setId(currentCliente.getId());
        return personaJPARepository.save(persona);
    }

    @Override
    public void deletePersona(int id) {
        getPersonaById(id);
        personaJPARepository.deleteById(id);
    }

    @Override
    public Persona getPersonaById(int id) {
        return personaJPARepository.findById(id).orElseThrow( () -> new PersonaNoEncontradException("La persona no existe") );
    }

    @Override
    public Persona getPersonaByCedula(String cedula) {
        return personaJPARepository.findPersonaByCedula(cedula).orElseThrow( () -> new PersonaNoEncontradException("Cedula no encontrada") );
    }

    @Override
    public List<Persona> getAllPersonas() {
        return personaJPARepository.findAll();
    }

    @Override
    public Boolean validarPersonaById(int idPersona) {
            return personaJPARepository.existsById(idPersona);
    }
}
