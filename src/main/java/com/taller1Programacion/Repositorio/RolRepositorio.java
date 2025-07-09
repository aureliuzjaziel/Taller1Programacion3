package com.taller1Programacion.Repositorio;

import com.taller1Programacion.Entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {

}
