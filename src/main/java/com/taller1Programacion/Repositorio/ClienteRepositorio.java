package com.taller1Programacion.Repositorio;

import com.taller1Programacion.Entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {}