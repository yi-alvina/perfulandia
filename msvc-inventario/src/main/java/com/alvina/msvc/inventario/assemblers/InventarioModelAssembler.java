package com.alvina.msvc.inventario.assemblers;

import com.alvina.msvc.inventario.controllers.InventarioControllerV2;
import com.alvina.msvc.inventario.models.entity.Inventario;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>>{

    @Override
    public EntityModel<Inventario> toModel(Inventario entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(InventarioControllerV2.class).findById(entity.getInventarioId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withRel("inventarios")

        );
    }
}
