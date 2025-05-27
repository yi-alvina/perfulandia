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
    private Integer precioUnitario;
    private Integer precioTotal;
    private Integer Cantidad;
    private Integer Precio;
    private VentaDTO idVenta;
    private ProductosDTO idProducto;

}
