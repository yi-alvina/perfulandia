package com.tomzamora.msvc.venta.dtos;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor

public class VentaDTO {
    private String DetalleVenta;
    private Long Valor;
    private Long Cantidad;
    private Date Fecha;
    private String Cliente;
    private String Vendedor;
    private String EstadoVenta;
}
