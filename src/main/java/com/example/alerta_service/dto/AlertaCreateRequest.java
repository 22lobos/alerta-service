package com.example.alerta_service.dto;

import jakarta.validation.constraints.NotNull;

public class AlertaCreateRequest {

    @NotNull(message = "idUsuario es obligatorio")
    private Long idUsuario;

    private String comuna;
    private String especie;
    private String raza;
    private String color;
    private String estadoReporte;

    public AlertaCreateRequest() {}

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
}
