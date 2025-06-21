package com.alvina.msvc.inventario.services;

import com.alvina.msvc.inventario.clients.ProductoClientsRest;
import com.alvina.msvc.inventario.clients.SucursalClientsRest;
import com.alvina.msvc.inventario.models.entity.Inventario;
import com.alvina.msvc.inventario.repositories.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceImplTest {
    @Mock
    private ProductoClientsRest productoClientsRest;

    @Mock
    private SucursalClientsRest sucursalClientsRest;

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioServicelmpl inventarioService;

    @BeforeEach
    public void setUp() {

    }
    @Test
    public void findAll_terminaOK() {
        // Preparar el mock
        List<Inventario> retornoMock = List.of(new Inventario(1, 1L, 1L));
        when(inventarioRepository.findAll()).thenReturn(retornoMock);

        // Llamar al objeto real
        List<Inventario> retornoObjetoReal = inventarioService.findAll();

        // Evaluar respuesta objeto real
        assertEquals(retornoMock, retornoObjetoReal);
    }

    @Test
    public void findById_terminaOK() {

        // Preparar el mock
        Inventario retornoMock = new Inventario(1, 1L, 1L);
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(retornoMock));

        // Llamar al objeto real
        Inventario retornoObjetoReal = inventarioService.findById(1L);

        // Evaluar respuesta objeto real
        assertEquals(retornoMock, retornoObjetoReal);
    }

    @Test
    public void save_terminaOK_ElIDyaExisteEnBD() {

        // Preparar el mock
        Inventario retornoMock = new Inventario(1, 1L, 1L);
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(retornoMock));

        try {
            inventarioService.save(retornoMock);
            fail("Falla PU");
        } catch (Exception e) {
            // Si lanza catch es porque esta todo bien
        }
    }

    @Test
    public void save_terminaOK() {

        // Preparar el mock
        Inventario retornoMock = new Inventario(1, 1L, 1L);
        when(inventarioRepository.findById(1L)).thenReturn(Optional.empty());
        when(inventarioRepository.save(retornoMock)).thenReturn(retornoMock);

        // Llamar al objeto real
        Inventario retornoObjetoReal = inventarioService.save(retornoMock);

        // Evaluar respuesta objeto real
        assertEquals(retornoMock, retornoObjetoReal);
    }

    @Test
    public void deleteById_terminaOK() {

        try {
            inventarioService.deleteById(1L);
        } catch (Exception e) {
            fail("Falla PU", e);
        }

    }



}