package com.software.seguimiento_service.service;

import com.software.seguimiento_service.event.PrincipalReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SeguimientoService {

    private static final Logger log = LoggerFactory.getLogger(SeguimientoService.class);

    private final Map<String, PrincipalReceived> principalReceivedMap = new ConcurrentHashMap<>();

    public void register(PrincipalReceived principalReceived) {
        log.info("Registrando PrincipalReceived con principalId: {}", principalReceived.principalId());
        log.info("Origen: {}", principalReceived.origen());
        log.info("Destino: {}", principalReceived.destino());

        principalReceivedMap.put(principalReceived.principalId(), principalReceived);
    }

    public Optional<PrincipalReceived> findById(String principalId) {
        log.info("Buscando PrincipalReceived con principalId: {}", principalId);
        return Optional.ofNullable(principalReceivedMap.get(principalId));
    }
}
