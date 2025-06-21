package com.alvina.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categoria_producto")
@Getter @Setter @ToString
@Schema(description = "Entidad que registra un producto en una categoria")
public class RegistroCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_id")
    @Schema(description = "Codigo de registro", example = "1")
    private Long registroId;

    @ManyToOne
    @JoinColumn(name = "categoria_id",nullable = false)
    @NotNull(message = "El campo categoria no puede ser vacio")
    @JsonBackReference("categoria-producto")
    @Schema(description = "Codigo de categoria", example = "1")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    @NotNull(message = "El campo categoria producto no puede ser vacio")
    @JsonBackReference("Producto-categoria")
    @Schema(description = "Codigo de producto", example = "1")
    private Producto producto;



}
