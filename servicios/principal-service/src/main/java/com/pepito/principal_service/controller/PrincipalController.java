package com.pepito.principal_service.controller;

import com.pepito.principal_service.dto.PrincipalReceivedRequest;
import com.pepito.principal_service.event.PrincipalReceived;
import com.pepito.principal_service.producer.PrincipalEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/principales")
public class PrincipalController {
    private final PrincipalEventProducer producer;
    public PrincipalController(PrincipalEventProducer producer) {
        this.producer = producer;
    }
    @PostMapping("/receive")
    public ResponseEntity<PrincipalReceived> receive(@RequestBody PrincipalReceivedRequest request) {
        PrincipalReceived evento = new PrincipalReceived(
                UUID.randomUUID(),
                request.principalId(),
                request.sender(),
                request.recipient(),
                request.origen(),
                request.destino(),
                Instant.now()
        );
        producer.publish(evento);
        return ResponseEntity.accepted().body(evento);
    }
}
