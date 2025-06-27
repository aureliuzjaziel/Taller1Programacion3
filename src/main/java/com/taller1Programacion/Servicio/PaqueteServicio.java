package com.taller1Programacion.Servicio;

import com.taller1Programacion.Entidad.Paquete;
import com.taller1Programacion.Repositorio.PaqueteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaqueteServicio {
    private final PaqueteRepositorio paqueteRepositorio;

    public PaqueteServicio(PaqueteRepositorio paqueteRepositorio) {
        this.paqueteRepositorio = paqueteRepositorio;
    }

    public List<Paquete> listarTodos() {
        return paqueteRepositorio.findAll();
    }

    public Paquete guardar(Paquete paquete) {
        return paqueteRepositorio.save(paquete);
    }

    public Paquete buscarPorId(Long id) {
        return paqueteRepositorio.findById(id).orElse(null);
    }
}