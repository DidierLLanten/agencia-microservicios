package com.agencia.viajes.config;

import com.agencia.viajes.service.interfaces.IDestinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Mensajes {
    private final IDestinoService destinoService;

    @RabbitListener(queues = Constantes.QUEUE )
    public Object receiveMessageAndReply(int idDestino) {
        boolean buscado = destinoService.validarDestinoById(idDestino);
        System.out.println("Buscado: (destino-service) " + buscado);
        return buscado;
    }
}
