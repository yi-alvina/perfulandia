package com.mcontreras.msvc.sucursal.service;

import com.mcontreras.msvc.sucursal.exceptions.SucursalException;
import com.mcontreras.msvc.sucursal.models.Sucursal;
import com.mcontreras.msvc.sucursal.repositories.SucursalRespository;
import com.mcontreras.msvc.sucursal.servicies.SucursalServicesImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {

    @Mock
    private SucursalRespository sucursalRespository;

    @InjectMocks
    private SucursalServicesImpl sucursalServices;

    private List<Sucursal> sucursalList = new ArrayList<>();

    private Sucursal sucursalPrueba;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of("es", "cl"));
        for (int i = 0; i < 10; i++) {
            Sucursal sucursal = new Sucursal();
            sucursal.setId((long) i + 1);
            sucursal.setNombreSucursal("Sucursal " + faker.company().name());
            sucursal.setDireccion(faker.address().fullAddress());

            this.sucursalList.add(sucursal);
        }
        this.sucursalPrueba = this.sucursalList.get(0);
    }

    @Test
    @DisplayName("Devuelve todas las sucursales")
    public void todasLasSucursales() {
        when(this.sucursalRespository.findAll()).thenReturn(this.sucursalList);
        List<Sucursal> result = sucursalServices.findAll();

        assertThat(result).hasSize(11);
        assertThat(result).contains(this.sucursalPrueba);

        verify(sucursalRespository, times(1)).findAll();
    }

    @Test
    @DisplayName("Encontrar una sucursal por id")
    public void encontrarUnaSucursalPorId() {
        when(sucursalRespository.findById(1L)).thenReturn(Optional.of(sucursalPrueba));
        Sucursal result = sucursalServices.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.sucursalList);

        verify(sucursalRespository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Consultar id de sucursal inexistente")
    public void consultarIdDeSucursalInexistente () {
        Long idInexistente = 1L;
        when(sucursalRespository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            sucursalServices.findById(idInexistente);
        }).isInstanceOf(SucursalException.class)
                .hasMessageContaining("La sucursal con id " + idInexistente
                        + " no se encuentra en la base de datos");
        verify(sucursalRespository,times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un usuario")
    public void debeGuardarUnUsuario() {
        when(sucursalRespository.save(any(Sucursal.class))).thenReturn(this.sucursalPrueba);
        Sucursal result = sucursalServices.save(this.sucursalPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.sucursalList);
        verify(sucursalRespository,times(1)).save(any(Sucursal.class));
    }

}
