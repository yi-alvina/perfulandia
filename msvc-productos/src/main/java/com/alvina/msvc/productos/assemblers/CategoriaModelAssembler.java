package com.alvina.msvc.productos.assemblers;

import com.alvina.msvc.productos.controllers.CategoriaControllerV2;
import com.alvina.msvc.productos.controllers.ProductoControllerV2;
import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.models.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {

    @Override
    public EntityModel<Categoria> toModel(Categoria entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(CategoriaControllerV2.class).findById(entity.getCategoriaId())).withSelfRel(),
                linkTo(methodOn(CategoriaControllerV2.class).findAll()).withRel("categorias")

        );
    }
}
