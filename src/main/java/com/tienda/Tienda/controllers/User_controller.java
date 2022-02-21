package com.tienda.Tienda.controllers;

import java.util.Optional;

import com.tienda.Tienda.models.User;
import com.tienda.Tienda.services.User_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User_controller {

    @Autowired
    private User_service repositorio;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/api/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id) {
        return repositorio.findById(id);
    }

    @PostMapping("/api/user")
    public User save(@RequestBody User nuevo) {
        nuevo.setPassword(encoder.encode(nuevo.getPassword()));
        return repositorio.save(nuevo);
    }

    @PutMapping("/api/user/{id}")
    public User update(@PathVariable("id") Integer id, @RequestBody User data) {

        Optional<User> actual = repositorio.findById(id);
        actual.get().setNombre(data.getNombre());
        actual.get().setApellidos(data.getApellidos());
        actual.get().setEmail(data.getEmail());
        actual.get().setPassword(data.getPassword());

        return repositorio.save(actual.get());
    }

    @DeleteMapping("/api/user/{id}")
    public void delete(@PathVariable Integer id) {
        repositorio.deleteById(id);
    }

}
