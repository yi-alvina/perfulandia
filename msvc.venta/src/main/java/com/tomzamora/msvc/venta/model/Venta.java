package com.tomzamora.msvc.venta.model;

import lombok.*;

import java.util.Date;

@Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor




public class Venta {

        private Long idVenta;
        private String DetalleVenta;
        private Long Valor;
        private Long Cantidad;
        private Date Fecha;
        private String Cliente;
        private String Vendedor;
        private String EstadoVenta;
}
