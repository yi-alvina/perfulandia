package com.tomzamora.msvc.carrito.msvc.carrito.repositories;

import com.tomzamora.msvc.carrito.msvc.carrito.dtos.CarritoDTO;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

}
