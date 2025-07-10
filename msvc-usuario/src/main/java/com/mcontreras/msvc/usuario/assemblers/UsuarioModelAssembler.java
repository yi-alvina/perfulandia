package com.mcontreras.msvc.usuario.assemblers;

import com.mcontreras.msvc.usuario.controllers.UsuarioControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.mcontreras.msvc.usuario.models.entities.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{

    @Override
    public EntityModel<Usuario> toModel(Usuario entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(UsuarioControllerV2.class).findById(entity.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).findAll()).withRel("usuarios"),
                linkTo(methodOn(UsuarioControllerV2.class).deletebyId(entity.getIdUsuario())).withRel("eliminar")
        );

    }
}
