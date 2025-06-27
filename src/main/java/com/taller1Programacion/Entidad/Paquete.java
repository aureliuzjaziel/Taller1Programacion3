package com.taller1Programacion.Entidad;

import jakarta.persistence.*;

@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaquete;
    private String nombre;
    private String descripcion;
    private Double precioAdulto;
    private Double precioNino;
    private Double precioAnciano;

    public Long getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Long idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioAdulto() {
        return precioAdulto;
    }

    public void setPrecioAdulto(Double precioAdulto) {
        this.precioAdulto = precioAdulto;
    }

    public Double getPrecioNino() {
        return precioNino;
    }

    public void setPrecioNino(Double precioNino) {
        this.precioNino = precioNino;
    }

    public Double getPrecioAnciano() {
        return precioAnciano;
    }

    public void setPrecioAnciano(Double precioAnciano) {
        this.precioAnciano = precioAnciano;
    }
}