package com.software.seguimiento_service.controller;

import com.software.seguimiento_service.event.PrincipalReceived;
import com.software.seguimiento_service.service.SeguimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seguimiento")
public class SeguimientoController {
    private final SeguimientoService seguimientoService;
    public SeguimientoController(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }
    @GetMapping("/{principalId}")
    public ResponseEntity<PrincipalReceived> find(@PathVariable String principalId) {
        return ResponseEntity.of(seguimientoService.findById(principalId));
    }
}
