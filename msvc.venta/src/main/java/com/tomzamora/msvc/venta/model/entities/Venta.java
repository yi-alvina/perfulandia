package com.tomzamora.msvc.venta.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@ToString @AllArgsConstructor @Entity
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
    private Date Fecha;

    @Column(name = "cliente")
    private Long idCliente;

    @Column(name = "sucursal")
    private Long idSucursal;

}
