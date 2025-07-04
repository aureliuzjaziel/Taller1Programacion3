package com.taller1Programacion.Controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {
    @GetMapping("/login")
    public String mostrarLogin() {
        return "/pages/login";
    }

    //acceso por roles
    @GetMapping("/postLogin")
    //authentication contiene los datos del  usuario autenticado en la sesion actual
    public String redirigirPorRol(Authentication authentication) {
        //crear un objeto (usuario) que representa al usuario autenticado
        User usuario = (User) authentication.getPrincipal();
        //obtener la lista de authorities o roles que tiene ese usuario
        String role = usuario.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("");
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (role.equals("ROLE_VENDEDOR")) {
            return "redirect:/index"; // Redirigir a la página de inicio para vendedores
        }
        return "redirect:/login?error";
    }
}