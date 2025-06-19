package com.mcontreras.msvc.sucursal.repositories;

import com.mcontreras.msvc.sucursal.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRespository extends JpaRepository<Sucursal, Long> {
}
