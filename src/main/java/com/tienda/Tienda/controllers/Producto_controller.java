package com.tienda.Tienda.controllers;

import com.tienda.Tienda.repositorio.Producto_repositorio;

import java.util.List;
import java.util.Optional;

import com.tienda.Tienda.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Producto_controller {

    @Autowired
    private Producto_repositorio repositorio;

    @GetMapping("/api/productos")
    public List<producto> getProductos() {
        return repositorio.findAll();
    }

    @GetMapping("/api/producto/{id}")
    public Optional<producto> getProducto(@PathVariable("id") Integer id) {
        return repositorio.findById(id);
    }

    @PostMapping("/api/producto")
    public producto save(@RequestBody producto nuevo) {
        return repositorio.save(nuevo);
    }

    @PutMapping("/api/producto/{id}")
    public producto update(@PathVariable("id") Integer id, @RequestBody producto data) {

        Optional<producto> actual = repositorio.findById(id);
        actual.get().setNombre(data.getNombre());
        actual.get().setDescripcion(data.getDescripcion());
        actual.get().setPrecio(data.getPrecio());
        actual.get().setStock(data.getStock());

        return repositorio.save(actual.get());
    }

    @DeleteMapping("/api/producto/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repositorio.deleteById(id);
    }

}
