package com.alvina.msvc.inventario.msvc_inventario.models;

import com.alvina.msvc.productos.models.Producto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mcontreras.msvc.usuario.models.Sucursal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "inventarios")
@Getter @Setter @NoArgsConstructor @ToString
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventario_id")
    private Long inventarioId;

    @Column(nullable = false)
    private Integer cantidadProducto;

    @JsonBackReference("inventario-producto")
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @NotNull(message = "El campo producto no puede ser vacio")
    private Producto producto;

    @JsonBackReference("inventario-sucursal")
    @OneToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

}
