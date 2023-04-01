package com.tienda.clientes.controller;

import com.tienda.clientes.entity.Cliente;
import com.tienda.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cliente")
@Tag(name="Servicios de los clientes",description = "Controlador encargado del crud completo de los clientes.")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/listAll")
    @Operation(description = "Servicio encargado de listar todos los clientes en la BD.")
    public ResponseEntity<?> getAllClientes(){
        List<Cliente> clientes;
        Map<String,Object> response=new HashMap<>();

        try {
            clientes=clienteService.findAll();
        } catch (DataAccessException e){
            response.put("error","Problema al conectar con la base de datos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e){
            response.put("error","El servicio no está disponible");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    @Operation(description = "Servicio encargado de listar un cliente por id.")
    public ResponseEntity<?> getById(@PathVariable("id") int id){

        Cliente cliente;
        Map<String,Object> response=new HashMap<>();

        try {
            cliente=clienteService.findById(id);
        } catch (DataAccessException e){
            response.put("error","Problema al conectar con la base de datos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e){
            response.put("error","El servicio no está disponible");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);


    }

    @PostMapping("/createCliente")
    @Operation(description = "Servicio encargado de insertar nuevos clientes en la BD.")
    public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result){

        Cliente clienteNuevo;
        Map<String,Object> response=new HashMap<>();

        if (result.hasErrors()){
            response.put("error","Alguno de los parámetros dado no está en el formato correcto");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            clienteNuevo=clienteService.save(cliente);
        } catch (DataAccessException e){
            response.put("error","Problema al conectar con la base de datos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e){
            response.put("error","El servicio no está disponible");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/updateCliente")
    @Operation(description = "Servicio encargado de modificar un cliente de la BD.")
    public ResponseEntity<?> updateUsers(@Valid @RequestBody Cliente cliente, BindingResult result){

        Cliente clienteViejo;
        Map<String,Object> response=new HashMap<>();

        try {
            if (result.hasErrors()){
                response.put("error","Alguno de los parámetros dado no está en el formato correcto");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            clienteViejo=clienteService.findById(cliente.getId());
            if (clienteViejo == null){
                response.put("error","El cliente con el id: "+cliente.getId()+" no se encuentra en la base de datos");
                return new ResponseEntity<>(cliente, HttpStatus.BAD_REQUEST);
            }
            clienteViejo=clienteService.save(cliente);
        } catch (DataAccessException e){
            response.put("error","Problema al conectar con la base de datos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e){
            response.put("error","El servicio no está disponible");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/borrarCliente/{id}")
    @Operation(description = "Servicio encargado de eliminar un cliente de la BD.")
    public ResponseEntity<?> borrarCliente(@PathVariable("id") int id){

        Cliente cliente;
        cliente=clienteService.findById(id);
        Map<String,Object> response=new HashMap<>();


        try {
            if (cliente == null) {
                response.put("error", "El cliente con el id: " + id + " no se encuentra en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            clienteService.delete(id);

        } catch (DataAccessException e){
            response.put("error","Problema al conectar con la base de datos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e){
            response.put("error","El servicio no está disponible");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("successfull", "El cliente con el id: " + id + " ha sido eliminado de la base de datos");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
