package com.tienda.Tienda.controllers;

import java.util.Optional;

import com.tienda.Tienda.models.User;
import com.tienda.Tienda.repositorio.User_repositorio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User_controller {

    private User_repositorio repositorio;

    @GetMapping("/cliente/{id}")
    public Optional<User> getCliente(@PathVariable("id") Integer id) {
        return repositorio.findById(id);
    }

    @PostMapping("/user")
    public User save(@RequestBody User nuevo) {
        return repositorio.save(nuevo);
    }

    @PutMapping("/cliente")
    public User update(@PathVariable("id") Integer id, @RequestBody User data) {

        Optional<User> actual = repositorio.findById(id);
        actual.get().setNombre(data.getNombre());
        actual.get().setApellidos(data.getApellidos());
        actual.get().setEmail(data.getEmail());
        actual.get().setPassword(data.getPassword());

        return repositorio.save(actual.get());
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Integer id) {
        repositorio.deleteById(id);
    }

}
