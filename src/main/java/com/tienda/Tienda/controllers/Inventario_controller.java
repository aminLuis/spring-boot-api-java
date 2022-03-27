package com.tienda.Tienda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tienda.Tienda.models.Inventario;
import com.tienda.Tienda.services.Inventario_service;

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
public class Inventario_controller {

    @Autowired
    private Inventario_service repositorio;

    @GetMapping("/api/inventario")
    public List<Inventario> getInventarios() {
        return repositorio.findAll();
    }

    @GetMapping("/api/inventario/{id}")
    public ResponseEntity<?> getInventario(@PathVariable("id") Integer id) {
        Optional<Inventario> inventario = null;
        Map<String, Object> response = new HashMap<>();

        try {
            inventario = repositorio.findById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (inventario == null) {
            response.put("Mensaje", "El inventario " + id + " no fue encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Inventario>(inventario.get(), HttpStatus.OK);

    }

    @PostMapping("/api/inventario")
    public ResponseEntity<?> save(@Valid @RequestBody Inventario nuevo, BindingResult result) {
        Inventario inventario = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo " + err.getField() + " " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            inventario = repositorio.save(nuevo);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al registrar en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(inventario, HttpStatus.CREATED);

    }

    @PutMapping("/api/inventario/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id, @RequestBody Inventario data,
            BindingResult result) {
        Optional<Inventario> actual = repositorio.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (actual == null) {
            response.put("Mensaje", "El inventario " + id + " no fue encontrado");
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
            actual.get().setDescripcion(data.getDescripcion());
            actual.get().setFecha(data.getFecha());
            actual.get().setEstado(data.getEstado());

            repositorio.save(actual.get());

        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al buscar inventario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actual.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/inventario/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            repositorio.deleteById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al eliminar inventario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje", "Registro eliminado");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
