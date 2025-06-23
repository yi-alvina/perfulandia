package com.alvina.msvc.inventario.controllers;

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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
@Validated
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Obtiene todos los inventarios", description = "Devuelve un List de Inventarios en el Body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa")
    })
    public ResponseEntity<List<Inventario>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findAll());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un inventario", description = "A través del id suministrado devuelve el inventario con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
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
            @Parameter(name="id", description = "Este es el id unico del medico", required = true)
    })
    public ResponseEntity<Inventario> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventarioService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable Long id) {
        inventarioService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping
    @Operation(
            summary = "Guarda un inventario",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guardado exitoso"),
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
    public ResponseEntity<Inventario> save(@Valid @RequestBody Inventario inventario){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventarioService.save(inventario));
    }
}

