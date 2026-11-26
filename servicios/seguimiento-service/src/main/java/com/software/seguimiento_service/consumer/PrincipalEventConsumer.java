package com.software.seguimiento_service.consumer;

import com.software.seguimiento_service.event.PrincipalReceived;
import com.software.seguimiento_service.service.SeguimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PrincipalEventConsumer {
    private static final Logger log = LoggerFactory.getLogger(PrincipalEventConsumer.class);

    private final SeguimientoService seguimientoService;
    public PrincipalEventConsumer(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }
    @KafkaListener(topics = "principal-events")
    public void consumir(PrincipalReceived evento){
        log.info("Evento recibido desde Kafka en topic principal-events. eventoId: {}, principalId: {}",
                evento.eventoId(), evento.principalId());

        seguimientoService.register(evento);

        log.info("Evento procesado correctamente. principalId: {}", evento.principalId());
    }
}
