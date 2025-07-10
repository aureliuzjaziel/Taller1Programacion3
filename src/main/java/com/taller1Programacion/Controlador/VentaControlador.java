package com.taller1Programacion.Controlador;

import com.taller1Programacion.Entidad.Usuario;
import com.taller1Programacion.Entidad.Venta;
import com.taller1Programacion.Servicio.ClienteServicio;
import com.taller1Programacion.Servicio.PaqueteServicio;
import com.taller1Programacion.Servicio.UsuarioServicio;
import com.taller1Programacion.Servicio.VentaServicio;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventas")
public class VentaControlador {
    private final VentaServicio ventaServicio;
    private final ClienteServicio clienteServicio;
    private final PaqueteServicio paqueteServicio;
    private final UsuarioServicio usuarioServicio;

    public VentaControlador(
            VentaServicio ventaServicio,
            ClienteServicio clienteServicio,
            PaqueteServicio paqueteServicio,
            UsuarioServicio usuarioServicio
    ) {
        this.ventaServicio = ventaServicio;
        this.clienteServicio = clienteServicio;
        this.paqueteServicio = paqueteServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaServicio.listarTodos());
        return "pages/listaVentas";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioVenta(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteServicio.listarTodos());
        model.addAttribute("paquetes", paqueteServicio.listarTodos());
        return "pages/formVenta";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta) {
        // Calcular subtotal, iva y total
        double subtotal =
                venta.getCantidadAdultos() * venta.getPaquete().getPrecioAdulto() +
                        venta.getCantidadNinos() * venta.getPaquete().getPrecioNino() +
                        venta.getCantidadAncianos() * venta.getPaquete().getPrecioAnciano();
        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        venta.setSubtotal(subtotal);
        venta.setIva(iva);
        venta.setTotal(total);

        // --- ASOCIAR USUARIO AUTENTICADO ---
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioServicio.buscarUsuarioPorUsername(username).orElse(null);
        if (usuario != null) {
            venta.setUsuario(usuario);
        }
        // -----------------------------------

        ventaServicio.guardar(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Venta venta = ventaServicio.buscarPorId(id);
        if (venta == null) {
            return "redirect:/ventas";
        }
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteServicio.listarTodos());
        model.addAttribute("paquetes", paqueteServicio.listarTodos());
        return "pages/formVenta";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVenta(@PathVariable Long id, @ModelAttribute Venta venta) {
        double subtotal =
                venta.getCantidadAdultos() * venta.getPaquete().getPrecioAdulto() +
                        venta.getCantidadNinos() * venta.getPaquete().getPrecioNino() +
                        venta.getCantidadAncianos() * venta.getPaquete().getPrecioAnciano();
        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        venta.setSubtotal(subtotal);
        venta.setIva(iva);
        venta.setTotal(total);

        venta.setIdVenta(id);

        // --- ASOCIAR USUARIO AUTENTICADO ---
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioServicio.buscarUsuarioPorUsername(username).orElse(null);
        if (usuario != null) {
            venta.setUsuario(usuario);
        }
        // -----------------------------------

        ventaServicio.guardar(venta);
        return "redirect:/ventas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaServicio.eliminarPorId(id);
        return "redirect:/ventas";
    }
}