package com.mcontreras.msvc.usuario.controllers;


import com.mcontreras.msvc.usuario.assemblers.UsuarioModelAssembler;
import com.mcontreras.msvc.usuario.dtos.ErrorDTO;
import com.mcontreras.msvc.usuario.models.entities.Usuario;
import com.mcontreras.msvc.usuario.servicies.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jfr.Category;
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
@RequestMapping("api/v2/usuarios")
@Validated
@Tag(
        name = "Usuario API HATEOAS",
        description = "Aquí se generar todos lo métodos CRUD de usuario"
)
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todos los usuarios", description = "Devuelve una Ñist de usuarios en el body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Category.class)
                    )
            )
    })

    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> findAll() {
        List<EntityModel<Usuario>> entityModels = this.usuarioService.findAll()
                .stream()
                .map(usuarioModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Usuario>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(UsuarioControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);


    }

    @GetMapping ("/{id}")
    @Operation(summary = "Obtiene un usuario", description = "A través del id suministrado devuelve el usuario con ese id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Usuario.class)
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application(json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )

    })

    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico del usuario", required = true)
    })
    public ResponseEntity<EntityModel<Usuario>> findById(@PathVariable Long id){
        EntityModel<Usuario> entityModel = this.usuarioModelAssembler.toModel(
                this.usuarioService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
        usuarioService.deleteUsuarioById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    @Operation(
            summary = "Guarda una categoria",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Usuario.class)

                    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "El categoria guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "categoria a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class)
            )
    )
    public ResponseEntity<EntityModel<Usuario>> create(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioNew = this.usuarioService.save(usuario);
        EntityModel<Usuario> entityModel = this.usuarioModelAssembler.toModel(usuarioNew);

        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).findById(usuarioNew.getIdUsuario())).toUri())
                .body(entityModel);
    }




}
