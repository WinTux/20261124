package com.pepito.principal_service.dto;

public record PrincipalReceivedRequest(
        String principalId,
        String sender,
        String recipient,
        String origen,
        String destino
) {
}
