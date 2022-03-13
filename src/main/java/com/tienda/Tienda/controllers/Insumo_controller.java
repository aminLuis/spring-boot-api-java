package com.tienda.Tienda.controllers;

import java.util.List;
import java.util.Optional;

import com.tienda.Tienda.models.Insumo;
import com.tienda.Tienda.models.Producto_insumo;
import com.tienda.Tienda.services.Insumo_service;
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
    public Optional<Insumo> getInsumo(@PathVariable("id") Integer id) {
        return repositorio.findById(id);
    }

    @PostMapping("/api/insumo")
    public Insumo save_insumo(@RequestBody Insumo nuevo) {
        return repositorio.save(nuevo);
    }

    @PutMapping("/api/insumo/{id}")
    public Insumo update_insumo(@PathVariable("id") Integer id, @RequestBody Insumo data) {

        Optional<Insumo> actual = repositorio.findById(id);
        actual.get().setNombre(data.getNombre());
        actual.get().setDescripcion(data.getDescripcion());
        actual.get().setCosto_unidad(data.getCosto_unidad());
        actual.get().setStock(data.getStock());
        actual.get().setId_inventario(data.getId_inventario());
        actual.get().setInventario(data.getInventario());

        return repositorio.save(actual.get());
    }

    @DeleteMapping("/api/insumo/{id}")
    public void delete_insumo(@PathVariable("id") Integer id) {
        repositorio.deleteById(id);
    }

    @PutMapping("/api/insumo/{id}/{id_pro}")
    public Insumo descontar_stock(@PathVariable("id") Integer id, @PathVariable("id_pro") Integer id_pro) {

        Optional<Producto_insumo> pro_insumo = repo_pro_insumo.findById(id_pro);
        Optional<Insumo> insumo = repositorio.findById(id);

        insumo.get().setStock(insumo.get().getStock() - pro_insumo.get().getRestar());
        return repositorio.save(insumo.get());

    }

}
