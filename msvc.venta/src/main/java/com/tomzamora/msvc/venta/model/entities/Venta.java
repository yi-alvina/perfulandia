package com.tomzamora.msvc.venta.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@ToString @AllArgsConstructor @Entity
@Table(name = "Venta")

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta", nullable = false, unique = true)
    private Long idVenta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Detalle_Venta", nullable = false, unique = true)
    private String DetalleVenta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Valor", nullable = false, unique = true)
    private Long Valor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cantidad", nullable = false, unique = true)
    private Long Cantidad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fecha", nullable = false, unique = true)
    private Date Fecha;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente", nullable = false, unique = true)
    private String Cliente;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vendedor", nullable = false, unique = true)
    private String Vendedor;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Estado_venta", nullable = false, unique = true)
    private String EstadoVenta;
}
