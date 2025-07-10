package com.tomzamora.msvc.carrito.msvc.carrito.controller;


import com.tomzamora.msvc.carrito.msvc.carrito.assemblers.CarritoModelAssembler;
import com.tomzamora.msvc.carrito.msvc.carrito.dtos.ErrorDTO;
import com.tomzamora.msvc.carrito.msvc.carrito.model.entities.Carrito;
import com.tomzamora.msvc.carrito.msvc.carrito.services.CarritoService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping
@Validated
@Tag(name = "Carritos V2", description = "Operaciones CRUD de carritos hateoas")
public class CarritoControllerV2 {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoModelAssembler carritoModelAssembler;


    @GetMapping
    @Operation(summary = "Obtiene el carrito ", description = "Devuelve un List de carrito en el Body ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Carrito.class)
                    )
            )

    })

    public ResponseEntity<CollectionModel<EntityModel<Carrito>>> findAll(){
        List<EntityModel<Carrito>> entityModels = this.carritoService.findAll()
                .stream()
                .map(carritoModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Carrito>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(CarritoControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un carrito", description = "A través del id suministrado devuelve un carrito con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Carrito.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carrito no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "aplication(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico del categoria", required = true)
    })
    public ResponseEntity<EntityModel<Carrito>> findById(@PathVariable Long id){
        EntityModel<Carrito> entityModel = this.carritoModelAssembler.toModel(
                this.carritoService .findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @PostMapping
    @Operation(
            summary = "Guarda un carrito",
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
                    schema = @Schema(implementation = Carrito.class)
            )
    )
    public ResponseEntity<EntityModel<Carrito>> create (@Valid @RequestBody Carrito carrito){
        Carrito carritoNew = this.carritoService.save(carrito);
        EntityModel<Carrito> entityModel = this.carritoModelAssembler.toModel(carritoNew);
        return  ResponseEntity
                .created(linkTo(methodOn(CarritoControllerV2.class).findById(carritoNew.getIdVenta())).toUri())
                .body(entityModel);
    }

}
