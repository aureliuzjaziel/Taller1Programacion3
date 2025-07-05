package com.taller1Programacion.Controlador;

import com.itextpdf.text.pdf.qrcode.Mode;
import com.taller1Programacion.Entidad.Rol;
import com.taller1Programacion.Entidad.Usuario;
import com.taller1Programacion.Repositorio.RolRepositorio;
import com.taller1Programacion.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        List<Rol> roles = rolRepositorio.findAll();
        model.addAttribute("roles", roles);
        return "pages/registroUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioServicio.registrarUsuario(usuario);
        return "redirect:/usuarios/registro?exito";
    }
}