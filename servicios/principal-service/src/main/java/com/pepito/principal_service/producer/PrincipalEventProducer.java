package com.pepito.principal_service.producer;

import com.pepito.principal_service.event.PrincipalReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PrincipalEventProducer {
    private static final Logger log = LoggerFactory.getLogger(PrincipalEventProducer.class);
    private static final String TOPIC = "principal-events";
    private final KafkaTemplate<String, PrincipalReceived> kafkaTemplate;
    public PrincipalEventProducer(KafkaTemplate<String, PrincipalReceived> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public CompletableFuture<SendResult<String, PrincipalReceived>> publish(PrincipalReceived evento) {
        log.info("Enviando evento PrincipalReceived al topic={}, key={}, eventoId={}",
                TOPIC, evento.principalId(), evento.eventoId());

        return kafkaTemplate.send(TOPIC, evento.principalId(), evento)
                .whenComplete((resultado, excepcion) -> {
                    if (excepcion != null) {
                        log.error("Error al enviar el evento PrincipalReceived al topic={}, key={}, eventoId={}",
                                TOPIC, evento.principalId(), evento.eventoId(), excepcion);
                    } else {
                        log.info("Evento PrincipalReceived enviado correctamente: topic={}, partition={}, offset={}, key={}, evento={}",
                                resultado.getRecordMetadata().topic(),
                                resultado.getRecordMetadata().partition(),
                                resultado.getRecordMetadata().offset(),
                                resultado.getProducerRecord().key(),
                                evento);
                    }
                });
    }
}
