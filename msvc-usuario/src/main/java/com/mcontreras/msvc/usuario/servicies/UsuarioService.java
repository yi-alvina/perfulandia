package com.mcontreras.msvc.usuario.servicies;


import com.mcontreras.msvc.usuario.models.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById (Long id);
    Usuario save(Usuario usuario);

}
