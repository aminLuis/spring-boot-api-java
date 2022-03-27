package com.tienda.Tienda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tienda.Tienda.models.Cliente;
import com.tienda.Tienda.services.Cliente_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cliente_controller {

    @Autowired
    private Cliente_service repositorio;

    @GetMapping("/api/cliente")
    public List<Cliente> getClientes() {
        return repositorio.findAll();
    }

    @GetMapping("/api/cliente/{id}")
    public ResponseEntity<?> getCliente(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente = repositorio.findById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("Mensaje", "El cliente " + id + " no fue encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);

    }

    @PostMapping("/api/cliente")
    public ResponseEntity<?> save(@Valid @RequestBody Cliente nuevo, BindingResult result) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo " + err.getField() + " " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            cliente = repositorio.save(nuevo);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al registrar en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/api/cliente/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody Cliente data, BindingResult result) {
        Optional<Cliente> actual = repositorio.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (actual == null) {
            response.put("Mensaje", "El cliente " + id + " no fue encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo " + err.getField() + " " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("Error", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            actual.get().setCedula(data.getCedula());
            actual.get().setNombre(data.getNombre());
            actual.get().setApellidos(data.getApellidos());
            actual.get().setLatitud(data.getLatitud());
            actual.get().setLongitud(data.getLongitud());
            actual.get().setTelefono(data.getTelefono());

            repositorio.save(actual.get());

        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al buscar cliente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actual.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            repositorio.deleteById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al eliminar cliente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje", "Registro eliminado");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
