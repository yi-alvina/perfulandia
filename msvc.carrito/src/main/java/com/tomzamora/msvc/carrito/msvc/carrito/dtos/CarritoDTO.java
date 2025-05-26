package com.tomzamora.msvc.carrito.msvc.carrito.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CarritoDTO {

    private Long idCarrito;
    private Long idUsuario;
    private Integer precioUnitario;
    private String nombreUsuario;
    private Date fecha;
    private Integer precioTotal;
    private Integer Cantidad;
    private Integer Precio;

}
