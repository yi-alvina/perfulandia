package com.tomzamora.msvc.carrito.msvc.carrito.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ProductosDTO {

    private String nombreProducto;
    private String palabraClave;
    private Integer precio;
}
