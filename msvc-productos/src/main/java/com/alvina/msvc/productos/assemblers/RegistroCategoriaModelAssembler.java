package com.alvina.msvc.productos.assemblers;

import com.alvina.msvc.productos.controllers.CategoriaControllerV2;
import com.alvina.msvc.productos.models.Categoria;
import com.alvina.msvc.productos.models.RegistroCategoria;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegistroCategoriaModelAssembler implements RepresentationModelAssembler<RegistroCategoria, EntityModel<RegistroCategoria>> {

    @Override
    public EntityModel<RegistroCategoria> toModel(RegistroCategoria entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(CategoriaControllerV2.class).findById(entity.getRegistroId())).withSelfRel(),
                linkTo(methodOn(CategoriaControllerV2.class).findAll()).withRel("registro de categorias")

        );
    }

}
