package com.taller1Programacion.Controlador;


import com.taller1Programacion.Entidad.Rol;
import com.taller1Programacion.Entidad.Usuario;

import com.taller1Programacion.Servicio.RolServicio;
import com.taller1Programacion.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private RolServicio rolServicio;

    //leer usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServicio.mostrarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "pages/listaUsuarios";
    }
    //insertar usuario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        List<Rol> roles= rolServicio.mostrarRoles();
        model.addAttribute("roles", roles);
        return "pages/registroUsuario";
    }
    //guardar usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }
    //editar usuario
    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioServicio.buscarUsuarioPorId(id);
        model.addAttribute("usuario", usuario.orElse(new Usuario()));
        List<Rol> roles= rolServicio.mostrarRoles();
        model.addAttribute("roles", roles);
        return "pages/registroUsuario";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}