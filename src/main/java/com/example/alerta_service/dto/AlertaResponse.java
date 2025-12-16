package com.example.alerta_service.dto;

import java.time.LocalDateTime;

public class AlertaResponse {

    private Long id;
    private Long idUsuario;
    private String comuna;
    private String especie;
    private String raza;
    private String color;
    private String estadoReporte;
    private Boolean activa;
    private LocalDateTime creadaEn;

    public AlertaResponse() {}

    public AlertaResponse(Long id, Long idUsuario, String comuna, String especie, String raza,
                          String color, String estadoReporte, Boolean activa, LocalDateTime creadaEn) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.comuna = comuna;
        this.especie = especie;
        this.raza = raza;
        this.color = color;
        this.estadoReporte = estadoReporte;
        this.activa = activa;
        this.creadaEn = creadaEn;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getEstadoReporte() { return estadoReporte; }
    public void setEstadoReporte(String estadoReporte) { this.estadoReporte = estadoReporte; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }

    public LocalDateTime getCreadaEn() { return creadaEn; }
    public void setCreadaEn(LocalDateTime creadaEn) { this.creadaEn = creadaEn; }
}
