package com.alvina.msvc.inventario.controllers;

import com.alvina.msvc.inventario.models.entity.Inventario;
import com.alvina.msvc.inventario.services.InventarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Test
    public void findAll_terminaOK() throws Exception {

        Inventario inventario = new Inventario(1L,32,1L,1L);
        when(inventarioService.findAll()).thenReturn(List.of(inventario));

        mockMvc.perform(get("/api/v1/inventario")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void findById_terminaOK() throws Exception {

        Inventario inventario = new Inventario(1L,32,1L,1L);
        when(inventarioService.findById(1L)).thenReturn(inventario);

        mockMvc.perform(get("/api/v1/inventario/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.inventarioId").value(Optional.of(1L)));
    }

    @Test
    public void deleteById_terminaOK() throws Exception {

        doNothing().when(inventarioService).deleteById(1L);

        mockMvc.perform(delete("/api/v1/inventario/1")).andExpect(status().isOk());
    }

    @Test
    public void save_terminaOK() throws Exception {

        Inventario guardado = new Inventario(1L,50,1L,1L);
        when(inventarioService.save(any(Inventario.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/v1/inventario").contentType(MediaType.APPLICATION_JSON).content("""
				{
				  "cantidadProducto": 50,
				  "idProducto": 1,
				  "idSucursal": 1
				}
				""")).andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.inventarioId").value(Optional.of(1L))).andExpect(jsonPath("$.cantidadProducto").value(Optional.of(50)));
    }

}
