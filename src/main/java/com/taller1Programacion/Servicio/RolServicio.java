package com.taller1Programacion.Servicio;


import com.taller1Programacion.Entidad.Rol;
import com.taller1Programacion.Repositorio.RolRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    public List<Rol> mostrarRoles() {
        return rolRepositorio.findAll();
    }
}
