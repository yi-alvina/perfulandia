package com.mcontreras.msvc.usuario.repositories;

import com.mcontreras.msvc.usuario.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRespository extends JpaRepository<Sucursal, Long> {
}
