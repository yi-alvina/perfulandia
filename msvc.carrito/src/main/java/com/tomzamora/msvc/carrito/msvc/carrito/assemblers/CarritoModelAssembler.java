package com.tomzamora.msvc.carrito.msvc.carrito.assemblers;


import com.tomzamora.msvc.carrito.msvc.carrito.controller.CarritoControllerV2;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @Override
    public EntityModel<Carrito> toModel(Carrito entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(CarritoControllerV2.class).findById (entity.getIdVenta())).withSelfRel(),
                linkTo(methodOn(CarritoControllerV2.class).findAll()).withRel("productos")

        );
    }
}





