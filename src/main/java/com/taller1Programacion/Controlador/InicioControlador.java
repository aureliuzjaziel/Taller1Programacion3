package com.taller1Programacion.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioControlador {

    @GetMapping("/pages/inicioSesion")
    public String mostrarInicioSesion() {
        return "pages/inicioSesion";
    }
}
