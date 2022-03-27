package com.tienda.Tienda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tienda.Tienda.models.Producto_insumo;
import com.tienda.Tienda.services.Producto_insumo_service;

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
public class Producto_insumo_controller {

    @Autowired
    private Producto_insumo_service repositorio;

    @GetMapping("/api/producto_insumo")
    public List<Producto_insumo> getInsumos() {
        return repositorio.findAll();
    }

    @PostMapping("/api/producto_insumo")
    public ResponseEntity<?> save_producto_insumo(@Valid @RequestBody Producto_insumo nuevo, BindingResult result) {
        Producto_insumo producto_insumo = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo " + err.getField() + " " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            producto_insumo = repositorio.save(nuevo);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al registrar en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(producto_insumo, HttpStatus.CREATED);

    }

    @PutMapping("/api/producto_insumo/{id}")
    public ResponseEntity<?> update_producto_insumo(@Valid @PathVariable("id") Integer id,
            @RequestBody Producto_insumo data, BindingResult result) {
        Optional<Producto_insumo> actual = repositorio.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (actual == null) {
            response.put("Mensaje", "El producto_insumo " + id + " no fue encontrado");
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
            actual.get().setId_producto(data.getId_producto());
            actual.get().setId_insumo(data.getId_insumo());
            actual.get().setDescripcion(data.getDescripcion());
            actual.get().setRestar(data.getRestar());

            repositorio.save(actual.get());

        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al buscar cliente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actual.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/producto_insumo/{id}")
    public ResponseEntity<?> delete_producto_insumo(@PathVariable("id") Integer id) {
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

    @GetMapping("/api/producto_insumo/asignaciones/{id_producto}")
    public List<Producto_insumo> getAsignaciones(@PathVariable("id_producto") Integer id_producto) {
        return repositorio.findById_producto(id_producto);
    }

}
