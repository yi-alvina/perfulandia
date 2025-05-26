package com.alvina.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categoria_producto")
@Getter @Setter @ToString
public class RegistroCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_id")
    private Long registroId;

    @ManyToOne
    @JoinColumn(name = "categoria_id",nullable = false)
    @NotNull(message = "El campo categoria no puede ser vacio")
    @JsonBackReference("categoria-producto")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    @NotNull(message = "El campo categoria producto no puede ser vacio")
    @JsonBackReference("Producto-categoria")
    private Producto producto;



}
