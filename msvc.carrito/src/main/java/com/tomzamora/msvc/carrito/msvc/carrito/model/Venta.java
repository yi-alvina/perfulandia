package com.tomzamora.msvc.carrito.msvc.carrito.model;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Venta {

        private Long idVenta;
        private Integer Valor;
        private Integer Cantidad;
        private Date Fecha;
        private Long idCliente;

}
