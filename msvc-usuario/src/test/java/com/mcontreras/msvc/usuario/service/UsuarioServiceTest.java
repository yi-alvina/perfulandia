package com.mcontreras.msvc.usuario.service;

import com.mcontreras.msvc.sucursal.exceptions.UsuarioException;
import com.mcontreras.msvc.sucursal.models.entities.Usuario;
import com.mcontreras.msvc.sucursal.repositories.UsuarioRespository;
import com.mcontreras.msvc.sucursal.servicies.UsuarioServiceImpl;
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
public class UsuarioServiceTest {

    @Mock
    private UsuarioRespository usuarioRespository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private List<Usuario> usuarioList = new ArrayList<>();

    private Usuario usuarioPrueba;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of("es","cl"));
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario((long) i+1);
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());

            String numeroStr = faker.idNumber().valid().replaceAll("-", "");
            String ultimo = numeroStr.substring(numeroStr.length() - 1);
            String restante  = numeroStr.substring(0, numeroStr.length() - 1);
            usuario.setRun(restante + "-" + ultimo);

            this.usuarioList.add(usuario);
        }
        this.usuarioPrueba = this.usuarioList.get(0);
    }

    @Test
    @DisplayName("Devuelve todos los usuarios")
    public void deveListarTodosLosUsuarios() {
        when(usuarioRespository.findAll()).thenReturn(this.usuarioList);
        List<Usuario> result = usuarioService.findAll();
        assertThat(result).hasSize(11);
        assertThat(result).contains(this.usuarioPrueba);

        verify(usuarioRespository, times(1)).findAll();
    }

    @Test
    @DisplayName("Encontrar a un usuario con ID")
    public void encontrarPorIdUnUsuario () {
        when(usuarioRespository.findById(1L)).thenReturn(Optional.of(usuarioPrueba));
        Usuario result = usuarioService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.usuarioPrueba);

        verify(usuarioRespository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Encontrar por id un usuario inexistente")
    public void encontrarPorIdUnUsuarioInexistente() {
        Long idInexistente = 1L;
        when(usuarioRespository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            usuarioService.findById(idInexistente);
        }).isInstanceOf(UsuarioException.class)
                .hasMessageContaining("El medico con id " + idInexistente
                        + " no se encuentra en la base de datos");
        verify(usuarioRespository,times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un usuario")
    public void debeGuardarUnUsuario() {
        when(usuarioRespository.save(any(Usuario.class))).thenReturn(this.usuarioPrueba);
        Usuario result = usuarioService.save(this.usuarioPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.usuarioPrueba);
        verify(usuarioRespository,times(1)).save(any(Usuario.class));
    }
}
