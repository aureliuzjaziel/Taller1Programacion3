package com.taller1Programacion.Servicio;

import com.taller1Programacion.Entidad.Venta;
import com.taller1Programacion.Repositorio.VentaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServicio {
    private final VentaRepositorio ventaRepositorio;

    public VentaServicio(VentaRepositorio ventaRepositorio) {
        this.ventaRepositorio = ventaRepositorio;
    }

    public List<Venta> listarTodos() {
        return ventaRepositorio.findAll();
    }

    public Venta guardar(Venta venta) {
        return ventaRepositorio.save(venta);
    }

    public Venta buscarPorId(Long id) {
        return ventaRepositorio.findById(id).orElse(null);
    }
}