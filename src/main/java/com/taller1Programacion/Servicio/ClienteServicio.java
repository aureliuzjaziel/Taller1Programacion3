package com.taller1Programacion.Servicio;

import com.taller1Programacion.Entidad.Cliente;
import com.taller1Programacion.Repositorio.ClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {
    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public List<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    public void eliminarPorId(Long id) {
        clienteRepositorio.deleteById(id);
    }
}