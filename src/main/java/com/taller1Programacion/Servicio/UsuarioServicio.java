package com.taller1Programacion.Servicio;

import com.taller1Programacion.Entidad.Usuario;
import com.taller1Programacion.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //mostrar los usuarios
    public List<Usuario> mostrarUsuarios() {
        return usuarioRepositorio.findAll( );
    }
    //Buscar usuario por username
    public Optional<Usuario> buscarUsuarioPorUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }

    //Buscar usuario por id
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    //Guardar usuario
    public Usuario guardarUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardar
        String passwordEncriptado = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptado);

        return usuarioRepositorio.save(usuario);
    }
    //eliminar usuario
    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
