package com.tienda.clientes.service;

import com.tienda.clientes.dao.ClienteDao;
import com.tienda.clientes.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteDao clienteDao;

    @Override
    public Cliente findById(int id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    public void delete(int id) {
        clienteDao.deleteById(id);
    }
}
