package com.pepito.principal_service.event;

import java.time.Instant;
import java.util.UUID;

public record PrincipalReceived (UUID eventoId,
                                 String principalId,
                                 String sender,
                                 String recipient,
                                 String origen,
                                 String destino,
                                 Instant timestamp){

}
