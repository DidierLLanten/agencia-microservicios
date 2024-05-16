package com.agencia.viajes.config;

import com.agencia.viajes.service.interfaces.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mensajes {
    private final IPersonaService personaService;


    @RabbitListener(queues = Constantes.QUEUE )
    public Object receiveMessageAndReply(int idPersona) {
        boolean buscado = personaService.validarPersonaById(idPersona);
        System.out.println("Buscado: (persona-service) " + buscado);
        return buscado;
    }

}
