package com.tomzamora.msvc.carrito.msvc.carrito.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class VentaDTO {

    private Integer Valor;
    private Integer Cantidad;
    private LocalDateTime Fecha;
}
