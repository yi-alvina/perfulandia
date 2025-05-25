package com.alvina.msvc.productos.repositories;

import com.alvina.msvc.productos.models.RegistroCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroCategoriaRepository extends JpaRepository<RegistroCategoria, Long> {
}
