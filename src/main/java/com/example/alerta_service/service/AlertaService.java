package com.example.alerta_service.service;

import com.example.alerta_service.dto.*;
import com.example.alerta_service.exception.ApiException;
import com.example.alerta_service.model.Alerta;
import com.example.alerta_service.repository.AlertaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AlertaService {

    private final AlertaRepository repo;

    public AlertaService(AlertaRepository repo) {
        this.repo = repo;
    }

    // RN: máximo 5 alertas activas por usuario
    private static final int MAX_ALERTAS_ACTIVAS = 5;

    // ================= CREAR =================
    public AlertaResponse crear(AlertaCreateRequest req) {

        long activas = repo.countByIdUsuarioAndActivaTrue(req.getIdUsuario());
        if (activas >= MAX_ALERTAS_ACTIVAS) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "Límite alcanzado: máximo " + MAX_ALERTAS_ACTIVAS + " alertas activas por usuario.");
        }

        boolean duplicada = repo.findByIdUsuario(req.getIdUsuario()).stream()
                .filter(a -> Boolean.TRUE.equals(a.getActiva()))
                .anyMatch(a ->
                        eq(a.getComuna(), req.getComuna()) &&
                        eq(a.getEspecie(), req.getEspecie()) &&
                        eq(a.getRaza(), req.getRaza()) &&
                        eq(a.getColor(), req.getColor()) &&
                        eq(a.getEstadoReporte(), req.getEstadoReporte())
                );

        if (duplicada) {
            throw new ApiException(HttpStatus.CONFLICT,
                    "Ya existe una alerta activa con los mismos criterios.");
        }

        Alerta alerta = new Alerta();
        alerta.setIdUsuario(req.getIdUsuario());
        alerta.setComuna(clean(req.getComuna()));
        alerta.setEspecie(clean(req.getEspecie()));
        alerta.setRaza(clean(req.getRaza()));
        alerta.setColor(clean(req.getColor()));
        alerta.setEstadoReporte(clean(req.getEstadoReporte()));
        alerta.setActiva(true);
        alerta.setCreadaEn(LocalDateTime.now());

        repo.save(alerta);
        return toResponse(alerta);
    }

    // ================= ACTUALIZAR =================
    public AlertaResponse actualizar(Long id, AlertaUpdateRequest req) {

        Alerta alerta = repo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Alerta no encontrada."));

        if (!alerta.getIdUsuario().equals(req.getIdUsuario())) {
            throw new ApiException(HttpStatus.FORBIDDEN,
                    "No puedes modificar una alerta que no es tuya.");
        }

        if (req.getActiva() != null &&
            Boolean.TRUE.equals(req.getActiva()) &&
            Boolean.FALSE.equals(alerta.getActiva())) {

            long activas = repo.countByIdUsuarioAndActivaTrue(req.getIdUsuario());
            if (activas >= MAX_ALERTAS_ACTIVAS) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Límite alcanzado: máximo " + MAX_ALERTAS_ACTIVAS + " alertas activas por usuario.");
            }
        }

        alerta.setComuna(clean(req.getComuna()));
        alerta.setEspecie(clean(req.getEspecie()));
        alerta.setRaza(clean(req.getRaza()));
        alerta.setColor(clean(req.getColor()));
        alerta.setEstadoReporte(clean(req.getEstadoReporte()));

        if (req.getActiva() != null) {
            alerta.setActiva(req.getActiva());
        }

        repo.save(alerta);
        return toResponse(alerta);
    }

    // ================= ELIMINAR =================
    public void eliminar(Long id, Long idUsuario) {

        Alerta alerta = repo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Alerta no encontrada."));

        if (!alerta.getIdUsuario().equals(idUsuario)) {
            throw new ApiException(HttpStatus.FORBIDDEN,
                    "No puedes eliminar una alerta que no es tuya.");
        }

        repo.delete(alerta);
    }

    // ================= OBTENER =================
    @Transactional(readOnly = true)
    public AlertaResponse obtener(Long id) {
        Alerta alerta = repo.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Alerta no encontrada."));
        return toResponse(alerta);
    }

    // ================= LISTAR =================
    @Transactional(readOnly = true)
    public List<AlertaResponse> listarPorUsuario(Long idUsuario) {
        return repo.findByIdUsuario(idUsuario)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ================= MAPPER =================
    private AlertaResponse toResponse(Alerta a) {
        AlertaResponse r = new AlertaResponse();
        r.setId(a.getId());
        r.setIdUsuario(a.getIdUsuario());
        r.setComuna(a.getComuna());
        r.setEspecie(a.getEspecie());
        r.setRaza(a.getRaza());
        r.setColor(a.getColor());
        r.setEstadoReporte(a.getEstadoReporte());
        r.setActiva(a.getActiva());
        r.setCreadaEn(a.getCreadaEn());
        return r;
    }

    // ================= UTILS =================
    private String clean(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isBlank() ? null : t;
    }

    private boolean eq(String a, String b) {
        String x = (a == null) ? "" : a.trim().toLowerCase();
        String y = (b == null) ? "" : b.trim().toLowerCase();
        return x.equals(y);
    }
}
