package com.agencia.viajes.config;

import com.agencia.viajes.service.interfaces.IAlojamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mensajes {
    private final IAlojamientoService alojamientoService;

    @RabbitListener(queues = Constantes.QUEUE_2 )
    public Object receiveMessageAndReply(int idAlojamiento) {
        boolean buscado = alojamientoService.validarAlojamientoById(idAlojamiento);
        System.out.println("Buscado: (Alojamiento-service) " + buscado);
        return buscado;
    }
}
