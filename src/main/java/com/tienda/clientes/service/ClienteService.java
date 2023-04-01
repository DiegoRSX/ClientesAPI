package com.tienda.clientes.service;

import com.tienda.clientes.entity.Cliente;

import java.util.List;

public interface ClienteService {

    public Cliente findById(int id);

    public List<Cliente> findAll();

    public Cliente save(Cliente cliente);

    public void delete(int id);

}
