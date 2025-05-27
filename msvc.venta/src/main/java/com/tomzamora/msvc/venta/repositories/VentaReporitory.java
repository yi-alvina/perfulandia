package com.tomzamora.msvc.venta.repositories;

import com.tomzamora.msvc.venta.model.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface VentaReporitory extends JpaRepository<Venta,Long> {

    List<VentaReporitory> findBy(Long idVenta);
}
