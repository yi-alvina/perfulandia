package com.mcontreras.msvc.sucursal.repositories;

import com.mcontreras.msvc.sucursal.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long> {
}
