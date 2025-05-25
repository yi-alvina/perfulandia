package com.tomzamora.msvc.carrito.msvc.carrito.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {

    private Long idCarrito;
    private Long idUsuario;
    private Long precioUnitario;
    private String nombreUsuario;
    private Date fecha;
    private String detalleCarrito;
    private Long precioTotal;
    private Long Cantidad;
    private Long Precio;
}
