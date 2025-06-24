package com.tomzamora.msvc.venta.controller;


import com.tomzamora.msvc.carrito.msvc.carrito.assemblers.CarritoModelAssembler;
import com.tomzamora.msvc.carrito.msvc.carrito.services.CarritoService;
import com.tomzamora.msvc.venta.assemblers.VentaModelAssembler;
import com.tomzamora.msvc.venta.dtos.ErrorDTO;
import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.services.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping
@Validated
@Tag(name ="Venta V2", description = "Operaciones CRUD de ventas hateoas")
public class VentaControllerV2 {
    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaModelAssembler ventaModelAssembler;


    @GetMapping
    @Operation(summary = "Obtiene todas las ventas ", description = "Devuelve un List de ventas en el Body ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Venta.class)
                    )
            )

    })

    public ResponseEntity<CollectionModel<EntityModel<Venta>>> findAll(){
        List<EntityModel<Venta>> entityModels = this.ventaService.findAll()
                .stream()
                .map(ventaModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Venta>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(VentaControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una venta", description = "A través del id suministrado devuelve una venta con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Venta.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Venta no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "aplication(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
@Parameters(value = {
        @Parameter(name="id", description = "Este es el id unico del categoria", required = true)
})
public ResponseEntity<EntityModel<Venta>> findById(@PathVariable Long id){
    EntityModel<Venta> entityModel = this.ventaModelAssembler.toModel(
            this.ventaService .findById(id)
    );
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(entityModel);
}

    @PostMapping
    @Operation(
            summary = "Guarda una venta",
            description = "Con este metodo podemos evitar los datos mediante un body y realizar el guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = "aplicattion/json",
                            schema = @Schema(implementation = ErrorDTO.class)

            )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "inventario a crear",
            content = @Content(
                    mediaType = "apliccation/json",
                    schema = @Schema(implementation = Venta.class)
            )
    )
    public ResponseEntity<EntityModel<Venta>> create (@Valid @RequestBody Venta venta){
                Venta ventaNew = this.ventaService.save(venta);
                EntityModel<Venta> entityModel = this.ventaModelAssembler.toModel(ventaNew);
                return  ResponseEntity
                        .created(linkTo(methodOn(VentaControllerV2.class).findById(ventaNew.getIdVenta())).toUri())
                        .body(entityModel);
    }


}

