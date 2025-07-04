package com.taller1Programacion.Controlador;

import com.taller1Programacion.Entidad.Cliente;
import com.taller1Programacion.Servicio.ClienteServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {
    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    // Método para listar todos los clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteServicio.listarTodos());
        return "pages/listaClientes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "pages/formCliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteServicio.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteServicio.buscarPorId(id);
        if (cliente == null) {
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "pages/formCliente";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            clienteServicio.eliminarPorId(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el cliente porque tiene ventas/facturas asociadas.");
        }
        return "redirect:/clientes";
    }
}