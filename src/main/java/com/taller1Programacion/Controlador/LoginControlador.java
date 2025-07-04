package com.taller1Programacion.Controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginControlador {
    @GetMapping("/login")
    public String login() {
        return "pages/login"; // Retorna la vista de inicio de sesión
    }
}