package com.taller1Programacion.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {
    @GetMapping("/registro")
    public String registro() {
        return "pages/registro";
    }
}