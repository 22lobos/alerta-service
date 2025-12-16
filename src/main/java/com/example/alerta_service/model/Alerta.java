package com.example.alerta_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    private String comuna;
    private String especie;
    private String raza;
    private String color;
    private String estadoReporte;

    @Column(nullable = false)
    private Boolean activa;

    @Column(nullable = false)
    private LocalDateTime creadaEn;

    public Alerta() {
    }

    // ===== GETTERS =====
    public Long getId() { return id; }
    public Long getIdUsuario() { return idUsuario; }
    public String getComuna() { return comuna; }
    public String getEspecie() { return especie; }
    public String getRaza() { return raza; }
    public String getColor() { return color; }
    public String getEstadoReporte() { return estadoReporte; }
    public Boolean getActiva() { return activa; }
    public LocalDateTime getCreadaEn() { return creadaEn; }

    // ===== SETTERS =====
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public void setComuna(String comuna) { this.comuna = comuna; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setRaza(String raza) { this.raza = raza; }
    public void setColor(String color) { this.color = color; }
    public void setEstadoReporte(String estadoReporte) { this.estadoReporte = estadoReporte; }
    public void setActiva(Boolean activa) { this.activa = activa; }
    public void setCreadaEn(LocalDateTime creadaEn) { this.creadaEn = creadaEn; }
}
