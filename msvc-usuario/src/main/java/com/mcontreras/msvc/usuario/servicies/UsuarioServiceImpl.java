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
    public Usuario updateUsuarioById(Long Id, Usuario usuarioUpdate) {
        return usuarioRespository.findById(Id).map(usuario -> {
            usuario.setNombres(usuarioUpdate.getNombres());
            usuario.setApellidos(usuarioUpdate.getApellidos());
            usuario.setCorreo(usuarioUpdate.getCorreo());
            return usuarioRespository.save(usuario);
        }).orElseThrow(
                () -> new UsuarioException("El usuario con el id "+Id+" no fue encontrado")
        );

    }
    @Override
    public void deleteUsuarioById(Long usuarioId) {
        Usuario usuario = usuarioRespository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioException("El usuario con id " + usuarioId + " no fue encontrado"));

        usuarioRespository.delete(usuario);
    }



}
