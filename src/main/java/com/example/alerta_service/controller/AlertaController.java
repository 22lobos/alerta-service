package com.example.alerta_service.controller;

import com.example.alerta_service.dto.*;
import com.example.alerta_service.service.AlertaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    // PRUEBA RÁPIDA (si esto no responde, el controller no está cargado)
    @GetMapping("/ping")
    public String ping() {
        return "OK alerta-service";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaResponse crear(@RequestBody AlertaCreateRequest req) {
        return service.crear(req);
    }

    @PutMapping("/{id}")
    public AlertaResponse actualizar(@PathVariable Long id, @RequestBody AlertaUpdateRequest req) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id, @RequestParam Long idUsuario) {
        service.eliminar(id, idUsuario);
    }

    @GetMapping("/{id}")
    public AlertaResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<AlertaResponse> listar(@PathVariable Long idUsuario) {
        return service.listarPorUsuario(idUsuario);
    }
}
