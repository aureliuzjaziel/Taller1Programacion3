package com.taller1Programacion.Entidad;

import jakarta.persistence.*;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Paquete paquete;

    private Integer cantidadAdultos;
    private Integer cantidadNinos;
    private Integer cantidadAncianos;

    private Double subtotal;
    private Double iva;
    private Double total;

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Integer getCantidadAdultos() {
        return cantidadAdultos;
    }

    public void setCantidadAdultos(Integer cantidadAdultos) {
        this.cantidadAdultos = cantidadAdultos;
    }

    public Integer getCantidadNinos() {
        return cantidadNinos;
    }

    public void setCantidadNinos(Integer cantidadNinos) {
        this.cantidadNinos = cantidadNinos;
    }

    public Integer getCantidadAncianos() {
        return cantidadAncianos;
    }

    public void setCantidadAncianos(Integer cantidadAncianos) {
        this.cantidadAncianos = cantidadAncianos;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}