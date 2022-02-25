package com.tienda.Tienda.controllers;

import java.util.List;
import java.util.Optional;

import com.tienda.Tienda.models.Inventario;
import com.tienda.Tienda.services.Inventario_service;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Inventario> getInventario(@PathVariable("id") Integer id) {
        return repositorio.findById(id);
    }

    @PostMapping("/api/inventario")
    public Inventario save(@RequestBody Inventario nuevo) {
        return repositorio.save(nuevo);
    }

    @PutMapping("/api/inventario/{id}")
    public Inventario update(@PathVariable("id") Integer id, @RequestBody Inventario data) {

        Optional<Inventario> actual = repositorio.findById(id);
        actual.get().setDescripcion(data.getDescripcion());
        actual.get().setFecha(data.getFecha());
        actual.get().setEstado(data.getEstado());

        return repositorio.save(actual.get());
    }

    @DeleteMapping("/api/inventario/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repositorio.deleteById(id);
    }

}
