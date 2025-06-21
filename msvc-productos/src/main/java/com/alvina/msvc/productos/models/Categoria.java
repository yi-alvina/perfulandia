package com.alvina.msvc.productos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "Entidad que representa una categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id", unique = true, nullable = false)
    @Schema(description = "Codigo de la categoria", example = "1")
    private Long categoriaId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El campo categoria no puede ser nulo")
    @Schema(description = "Nombre de la categoria", example = "Juvenil")
    private String nombreCategoria;

}
