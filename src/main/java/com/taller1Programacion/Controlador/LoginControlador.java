package com.taller1Programacion.Controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {
    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }
    @GetMapping ("/postLogin")
public String redirigirPorRol(Authentication authentication) {
        User usuario = (User) authentication.getPrincipal();
        String role = usuario.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("");

        if (role.equals("ROLE_ADMINISTRADOR")) {
            return "redirect:/principal/index";
        } else if (role.equals("ROLE_VENDENDOR")) {
            return "redirect:/clientes";
        } else {
            return "redirect:/login?error";
        }
    }

}