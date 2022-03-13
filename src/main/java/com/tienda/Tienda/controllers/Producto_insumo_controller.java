package com.tienda.Tienda.controllers;

import java.util.List;
import java.util.Optional;

import com.tienda.Tienda.models.Producto_insumo;
import com.tienda.Tienda.services.Producto_insumo_service;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Producto_insumo save_producto_insumo(@RequestBody Producto_insumo nuevo) {
        return repositorio.save(nuevo);
    }

    @PutMapping("/api/producto_insumo/{id}")
    public Producto_insumo update_producto_insumo(@PathVariable("id") Integer id, @RequestBody Producto_insumo data) {

        Optional<Producto_insumo> actual = repositorio.findById(id);
        actual.get().setId_producto(data.getId_producto());
        actual.get().setId_insumo(data.getId_insumo());
        actual.get().setDescripcion(data.getDescripcion());
        actual.get().setRestar(data.getRestar());

        return repositorio.save(actual.get());
    }

    @DeleteMapping("/api/producto_insumo/{id}")
    public void delete_producto_insumo(@PathVariable("id") Integer id) {
        repositorio.deleteById(id);
    }

    @GetMapping("/api/producto_insumo/asignaciones/{id_producto}")
    public List<Producto_insumo> getAsignaciones(@PathVariable("id_producto") Integer id_producto) {
        return repositorio.findById_producto(id_producto);
    }

}
