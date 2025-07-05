package com.taller1Programacion.Entidad;

import jakarta.persistence.*;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    private String nombreRol; // ADMINISTRADOR, VENDEDOR

    // Getters y Setters
    public Long getIdRol() { return idRol; }
    public void setIdRol(Long idRol) { this.idRol = idRol; }
    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }
}
