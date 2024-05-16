package com.agencia.viajes.service;

import com.agencia.viajes.config.Constantes;
import com.agencia.viajes.model.Reserva;
import com.agencia.viajes.repository.ReservaRepository;
import com.agencia.viajes.service.exceptions.ReservaNoEncontradaException;
import com.agencia.viajes.service.interfaces.IReservaService;
import com.agencia.viajes.service.interfaces.IVueloService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final IVueloService vueloService;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public Reserva createReserva(Reserva reserva) {

        validarPersona(reserva.getIdPersona());
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva updateReserva(int id,Reserva reserva){
        vueloService.getVueloById(reserva.getVuelo().getId());
        Reserva currentReserva = getReservaById(id);
        reserva.setId(currentReserva.getId());
        return reservaRepository.save(reserva);
    }

    @Override
    public void deleteReserva(int id) {
        getReservaById(id);
        reservaRepository.deleteById(id);
    }

    @Override
    public Reserva getReservaById(int id) {
        return reservaRepository.findById(id).orElseThrow( () -> new ReservaNoEncontradaException("La Reserva no existe") );
    }

    @Override
    public List<Reserva> getAllReservas(){
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> getReservasByidPersona(int idPersona) {
        return reservaRepository.findReservaByidPersona(idPersona);
    }

    @Override
    public List<Reserva> getReservasByidAlojamiento(int idAlojamiento) {
        return reservaRepository.findReservasByIdAlojamiento(idAlojamiento);
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
