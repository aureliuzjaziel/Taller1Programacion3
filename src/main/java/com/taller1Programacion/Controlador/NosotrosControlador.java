package com.taller1Programacion.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NosotrosControlador {

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros/nosotros";
    }
}
