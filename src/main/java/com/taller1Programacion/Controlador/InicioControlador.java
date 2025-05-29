package com.taller1Programacion.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioControlador {
    @GetMapping("inicioSesion")
    public String login() {
        return "inicioSesion/inicioSesion";
    }
}
