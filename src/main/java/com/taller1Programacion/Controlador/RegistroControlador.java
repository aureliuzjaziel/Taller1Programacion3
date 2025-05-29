package com.taller1Programacion.Controlador;

import com.taller1Programacion.Modelo.Registro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {

    @GetMapping("registro")
    public String registro(Model model) {
        model.addAttribute("registro", new Registro());
        return "registro/registro";
    }
}