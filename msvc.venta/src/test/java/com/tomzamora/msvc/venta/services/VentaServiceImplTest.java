package com.tomzamora.msvc.venta.services;


import com.tomzamora.msvc.venta.clients.SucursalClientsRest;
import com.tomzamora.msvc.venta.clients.UsuarioClienteRest;
import com.tomzamora.msvc.venta.model.Sucursal;
import com.tomzamora.msvc.venta.model.entities.Venta;
import com.tomzamora.msvc.venta.repositories.VentaReporitory;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class VentaServiceImplTest {

    @Mock
    private UsuarioClienteRest usuarioClienteRest;

    @Mock
    private SucursalClientsRest sucursalClientsRest;

    @Mock
    private VentaReporitory ventaReporitory;

    @InjectMocks
    private VentaServiceImpl ventaServiceImpl;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of(:"es", "cl"));
        for (int i = 0; i < 10; i++) {
            Venta venta = new Venta();
            venta.setIdVenta();long) i + 1;
            venta.setCantidad("Venta"+ faker.company().name());
            venta.setFecha(faker.date().fullDate());

            this.ventaList.add(venta);

        }
        this.ventaPrueba = this.ventaList.get(0);
    }

    @Test
    @DisplayName("Encontrar una venta por id")
    public void consultarunaVentaPorId(){
        when(this.ventaReporitory.findAll().thenReturn(this.ventaList));
        List<Venta> result = ventaServiceImpl.findAll();

        assertThat(result).hasSize(10);
        assertThat(result).contains(this.ventaPrueba);

        verify(ventaReporitory, times (1).findById(idInexistente));
        public void consultarIdDeSucursalInexistente () {
            Long idInexistente = 1L;
            when(ventaReporitory.findById(idInexistente)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> {
                ventaServiceImpl.findById(idInexistente);
            }).isInstanceOf(ventaException.class)
                    .hasMessage("Sucursal con el id " + idInexistente + " no encontrado.");
            verify(ventaReporitory,times(1)).findById(idInexistente);
        }

        @Test
        @DisplayName("Debe guardar una venta")
        public void debeGuardarUnUsuario() {
            when(ventaReporitory.save(any(Sucursal.class))).thenReturn(this.ventaPrueba);
            Sucursal result = ventaServiceImpl.save(this.ventaPrueba);
            assertThat(result).isNotNull();
            assertThat(result).isEqualTo(this.ventaPrueba);
            verify(ventaReporitory,times(1)).save(any(Venta.class));
        }
    }
}
