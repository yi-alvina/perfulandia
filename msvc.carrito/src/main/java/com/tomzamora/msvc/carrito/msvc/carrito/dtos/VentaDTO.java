package com.tomzamora.msvc.carrito.msvc.carrito.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class VentaDTO {

    private Integer Valor;
    private Integer Cantidad;
    private Date Fecha;
}
