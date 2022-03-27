package com.tienda.Tienda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tienda.Tienda.models.Insumo;
import com.tienda.Tienda.models.Producto_insumo;
import com.tienda.Tienda.services.Insumo_service;
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
public class Insumo_controller {

    @Autowired
    private Insumo_service repositorio;
    @Autowired
    private Producto_insumo_service repo_pro_insumo;

    @GetMapping("/api/insumo")
    public List<Insumo> getInsumos() {
        return repositorio.findAll();
    }

    @GetMapping("/api/insumo/{id}")
    public ResponseEntity<?> getInsumo(@PathVariable("id") Integer id) {
        Optional<Insumo> insumo = null;
        Map<String, Object> response = new HashMap<>();

        try {
            insumo = repositorio.findById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (insumo == null) {
            response.put("Mensaje", "El insumo " + id + " no fue encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Insumo>(insumo.get(), HttpStatus.OK);
    }

    @PostMapping("/api/insumo")
    public ResponseEntity<?> save_insumo(@Valid @RequestBody Insumo nuevo, BindingResult result) {
        Insumo insumo = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo " + err.getField() + " " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            insumo = repositorio.save(nuevo);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al registrar en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(insumo, HttpStatus.CREATED);
    }

    @PutMapping("/api/insumo/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id, @RequestBody Insumo data,
            BindingResult result) {
        Optional<Insumo> actual = repositorio.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (actual == null) {
            response.put("Mensaje", "El insumo " + id + " no fue encontrado");
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
            actual.get().setNombre(data.getNombre());
            actual.get().setDescripcion(data.getDescripcion());
            actual.get().setCosto_unidad(data.getCosto_unidad());
            actual.get().setStock(data.getStock());
            actual.get().setId_inventario(data.getId_inventario());
            actual.get().setInventario(data.getInventario());

            repositorio.save(actual.get());

        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al buscar cliente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actual.get(), HttpStatus.CREATED);

    }

    @DeleteMapping("/api/insumo/{id}")
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

    @PutMapping("/api/insumo/{id}/{id_pro}")
    public Insumo descontar_stock(@PathVariable("id") Integer id, @PathVariable("id_pro") Integer id_pro) {

        Optional<Producto_insumo> pro_insumo = repo_pro_insumo.findById(id_pro);
        Optional<Insumo> insumo = repositorio.findById(id);

        insumo.get().setStock(insumo.get().getStock() - pro_insumo.get().getRestar());
        return repositorio.save(insumo.get());

    }

}
