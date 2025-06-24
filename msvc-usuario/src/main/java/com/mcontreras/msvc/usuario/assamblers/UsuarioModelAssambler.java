package com.mcontreras.msvc.sucursal.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.mcontreras.msvc.sucursal.models.entities.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{

    @Override
    public EntityModel<Usuario> toModel(Usuario entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getProductoId())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withRel("productos")

        );
    }
}
