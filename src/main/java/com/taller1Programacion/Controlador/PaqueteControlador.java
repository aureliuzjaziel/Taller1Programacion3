package com.taller1Programacion.Controlador;

import com.taller1Programacion.Entidad.Paquete;
import com.taller1Programacion.Servicio.PaqueteServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paquetes")
public class PaqueteControlador {
    private final PaqueteServicio paqueteServicio;

    public PaqueteControlador(PaqueteServicio paqueteServicio) {
        this.paqueteServicio = paqueteServicio;
    }

    @GetMapping
    public String listarPaquetes(Model model) {
        model.addAttribute("paquetes", paqueteServicio.listarTodos());
        return "pages/listaPaquetes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("paquete", new Paquete());
        return "pages/formPaquete";
    }

    @PostMapping("/guardar")
    public String guardarPaquete(@ModelAttribute Paquete paquete) {
        paqueteServicio.guardar(paquete);
        return "redirect:/paquetes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Paquete paquete = paqueteServicio.buscarPorId(id);
        if (paquete == null) {
            return "redirect:/paquetes";
        }
        model.addAttribute("paquete", paquete);
        return "pages/formPaquete";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        paqueteServicio.eliminarPorId(id);
        return "redirect:/paquetes";
    }
}