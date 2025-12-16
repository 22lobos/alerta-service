package com.example.alerta_service.repository;

import com.example.alerta_service.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByIdUsuario(Long idUsuario);
    long countByIdUsuarioAndActivaTrue(Long idUsuario);
}
