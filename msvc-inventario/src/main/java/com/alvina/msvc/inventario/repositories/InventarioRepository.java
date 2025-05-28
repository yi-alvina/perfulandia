package com.alvina.msvc.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alvina.msvc.inventario.models.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
