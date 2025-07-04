package com.taller1Programacion.Repositorio;

import com.taller1Programacion.Entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    Rol findByNombreRol(String nombreRol);
}