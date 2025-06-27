package com.taller1Programacion.Controlador;

import org.springframework.web.bind.annotation.GetMapping;

public class NosotrosControlador {
    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "pages/nosotros";
    }
}
