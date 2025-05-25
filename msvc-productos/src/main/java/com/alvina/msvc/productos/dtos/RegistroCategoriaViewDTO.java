package com.alvina.msvc.productos.dtos;

import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.models.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class RegistroCategoriaViewDTO {
    private Long registroId;
    private Long CategoriaId;
    private Producto producto;
    private Categoria categoria;
}
