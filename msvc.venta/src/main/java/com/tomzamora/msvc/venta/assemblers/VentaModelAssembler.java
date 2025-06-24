package com.tomzamora.msvc.venta.assemblers;


import com.tomzamora.msvc.venta.controller.VentaControllerV2;
import com.tomzamora.msvc.venta.model.entities.Venta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {


    @Override
    public EntityModel<Venta> toModel(Venta entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(VentaControllerV2.class).findById(entity.getIdVenta())).withSelfRel(),
                linkTo(methodOn(VentaControllerV2.class).findAll()).withRel("productos")

        );
    }
}




