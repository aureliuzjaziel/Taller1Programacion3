package com.taller1Programacion.Controlador;

import com.taller1Programacion.Entidad.Cliente;
import com.taller1Programacion.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/pages/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/registro";
    }

    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute Cliente cliente) {
        clienteServicio.guardarCliente(cliente);
        return "redirect:/";
    }
}