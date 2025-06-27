package com.taller1Programacion.Controlador;

import com.taller1Programacion.Entidad.Venta;
import com.taller1Programacion.Servicio.ClienteServicio;
import com.taller1Programacion.Servicio.PaqueteServicio;
import com.taller1Programacion.Servicio.VentaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventas")
public class VentaControlador {
    private final VentaServicio ventaServicio;
    private final ClienteServicio clienteServicio;
    private final PaqueteServicio paqueteServicio;

    public VentaControlador(VentaServicio ventaServicio, ClienteServicio clienteServicio, PaqueteServicio paqueteServicio) {
        this.ventaServicio = ventaServicio;
        this.clienteServicio = clienteServicio;
        this.paqueteServicio = paqueteServicio;
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

        ventaServicio.guardar(venta);
        return "redirect:/ventas";
    }
}