package com.tomzamora.msvc.carrito.msvc.carrito.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito", nullable = false, unique = true)
    private Long idCarrito;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long idUsuario;

    @Column(name = "precio_unitario", nullable = false, unique = true)
    @Pattern(regexp = "\\d{1,8}-[\\dkK]", message = "El precio debe ser registrado")
    private String precioUnitario;

    @Column(name = "nombre_usuario", nullable = false)
    @NotBlank(message = "El campo no puede ser vacio")
    private String nombreUsuario;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "producto", nullable = false)
    private Long idProducto;

    @Column(name = "precio_total", nullable = false)
    private Integer precioTotal;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio", nullable = false)
    private Integer precio;

}
