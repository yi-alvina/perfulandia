package com.tomzamora.msvc.venta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface VentaReporitory extends JpaRepository<VentaReporitory, Long> {

    List<VentaReporitory> findBy(Long idVenta);
}
