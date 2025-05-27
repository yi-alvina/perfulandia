package com.mcontreras.msvc.usuario.servicies;

import com.mcontreras.msvc.usuario.exceptions.UsuarioException;
import com.mcontreras.msvc.usuario.models.entities.Usuario;
import com.mcontreras.msvc.usuario.repositories.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Override
    public List<Usuario> findAll(){return this.findAll();}

    @Override
    public Usuario findById(Long id) {
        return this.usuarioRespository.findById(id).orElseThrow(
                () -> new UsuarioException("El usuarion con el ID " + id + " no se encuentra en la base de datos.")
        );
    }

    @Override
    public Usuario save(Usuario usuario){
        return this.usuarioRespository.save(usuario);
    }

    @Override
    public void deleteUsusarioById(Long usuarioId) {
        usuarioRespository.deleteById(usuarioId);
    }

}
