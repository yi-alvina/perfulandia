package com.alvina.msvc.productos.repositories;

import com.alvina.msvc.productos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombreCategoriaEquals(String nombreCategoria);
    Optional<Categoria> findByCategoriaIdEquals(Long categoriaId);

}
