package com.example.alerta_service.controller;

import com.example.alerta_service.dto.AlertaCreateRequest;
import com.example.alerta_service.dto.AlertaResponse;
import com.example.alerta_service.dto.AlertaUpdateRequest;
import com.example.alerta_service.service.AlertaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    // =========================
    // PING (PRUEBA DE VIDA)
    // =========================
    @GetMapping("/ping")
    public String ping() {
        return "alerta-service OK";
    }

    // =========================
    // CREAR ALERTA
    // =========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaResponse crearAlerta(@RequestBody AlertaCreateRequest request) {
        return alertaService.crear(request);
    }

    // =========================
    // ACTUALIZAR ALERTA
    // =========================
    @PutMapping("/{id}")
    public AlertaResponse actualizarAlerta(
            @PathVariable Long id,
            @RequestBody AlertaUpdateRequest request
    ) {
        return alertaService.actualizar(id, request);
    }

    // =========================
    // ELIMINAR ALERTA
    // =========================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAlerta(
            @PathVariable Long id,
            @RequestParam Long idUsuario
    ) {
        alertaService.eliminar(id, idUsuario);
    }

    // =========================
    // OBTENER ALERTA POR ID
    // =========================
    @GetMapping("/{id}")
    public AlertaResponse obtenerAlerta(@PathVariable Long id) {
        return alertaService.obtener(id);
    }

    // =========================
    // LISTAR ALERTAS POR USUARIO
    // =========================
    @GetMapping("/usuario/{idUsuario}")
    public List<AlertaResponse> listarPorUsuario(@PathVariable Long idUsuario) {
        return alertaService.listarPorUsuario(idUsuario);
    }
}
