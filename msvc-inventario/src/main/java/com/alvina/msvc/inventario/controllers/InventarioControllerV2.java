package com.alvina.msvc.inventario.controllers;

import ch.qos.logback.core.model.Model;
import com.alvina.msvc.inventario.assemblers.InventarioModelAssembler;
import com.alvina.msvc.inventario.dtos.ErrorDTO;
import com.alvina.msvc.inventario.models.entity.Inventario;
import com.alvina.msvc.inventario.services.InventarioService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/inventario")
@Validated
@Tag(name = "IInventarios V2", description = "Operaciones CRUD de inventarios hateoas")
public class InventarioControllerV2 {
    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioModelAssembler inventarioModelAssembler;
    private Object toModel;

    @GetMapping
    @Operation(summary = "Obtiene todos los inventarios", description = "Devuelve un List de Inventarios en el Body")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)
                    )
            )
    })

    public ResponseEntity<CollectionModel<EntityModel<Inventario>>> findAll() {
        List<EntityModel<Inventario>> entityModels = this.inventarioService.findAll()
                .stream()
                .map(inventarioModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Inventario>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un inventario", description = "A través del id suministrado devuelve el inventario con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Inventario no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })

    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico del inventario", required = true)
    })
    public ResponseEntity<EntityModel<Inventario>> findById(@PathVariable Long id){
        EntityModel<Inventario> entityModel = this.inventarioModelAssembler.toModel(
                this.inventarioService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @PostMapping
    @Operation(
            summary = "Guarda un inventario",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)

    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "El inventario guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "inventario a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<EntityModel<Inventario>> create(@Valid @RequestBody Inventario inventario){
        Inventario inventarioNew = this.inventarioService.save(inventario);
        EntityModel<Inventario> entityModel = this.inventarioModelAssembler.toModel(inventarioNew);

        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).findById(inventarioNew.getinventarioId())).toUri())
                .body(entityModel);
    }
}
