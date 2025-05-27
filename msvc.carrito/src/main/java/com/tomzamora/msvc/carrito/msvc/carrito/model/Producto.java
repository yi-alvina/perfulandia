package com.tomzamora.msvc.carrito.msvc.carrito.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Producto {


    private Long productoId;
    private Long nombreProducto;
    private Long palabraClave;
    private Long precio;

}
