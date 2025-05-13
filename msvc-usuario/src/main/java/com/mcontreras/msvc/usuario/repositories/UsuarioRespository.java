package com.mcontreras.msvc.usuario.repositories;

import com.mcontreras.msvc.usuario.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long> {
}
