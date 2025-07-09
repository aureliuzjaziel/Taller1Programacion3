package com.taller1Programacion.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControlador {
    @RequestMapping("/principal")
    @GetMapping("/index")
    public String mostrarInicio() {
        return "index";
    }
}