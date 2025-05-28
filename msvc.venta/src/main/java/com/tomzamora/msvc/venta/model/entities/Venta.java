package com.tomzamora.msvc.venta.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@ToString @Entity
@Table(name = "Venta")

public class

Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta", nullable = false, unique = true)
    private Long idVenta;

    @Column(name = "Valor")
    private Long Valor;

    @Column(name = "cantidad")
    private Long Cantidad;

    @Column(name = "fecha")
    private LocalDateTime Fecha;

    private Long idCliente;

    private Long idSucursal;

}
