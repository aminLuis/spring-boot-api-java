package com.tienda.Tienda.controllers;

import java.util.List;
import java.util.Optional;

import com.tienda.Tienda.models.Cliente;
import com.tienda.Tienda.repositorio.Cliente_repositorio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cliente_controller {

    private Cliente_repositorio repositorio;

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return repositorio.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Optional<Cliente> getCliente(@PathVariable("id") Integer id) {
        return repositorio.findById(id);
    }

    @PostMapping("/cliente")
    public Cliente save(@RequestBody Cliente nuevo) {
        return repositorio.save(nuevo);
    }

    @PutMapping("/cliente")
    public Cliente update(@PathVariable Integer id, @RequestBody Cliente data) {

        Optional<Cliente> actual = repositorio.findById(id);
        actual.get().setCedula(data.getCedula());
        actual.get().setNombre(data.getNombre());
        actual.get().setApellidos(data.getApellidos());
        actual.get().setDireccion(data.getDireccion());
        actual.get().setTelefono(data.getTelefono());

        return repositorio.save(actual.get());
    }

    @DeleteMapping("/cliente/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repositorio.deleteById(id);
    }

}