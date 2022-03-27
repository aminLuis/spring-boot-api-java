package com.tienda.Tienda.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tienda.Tienda.models.Users;
import com.tienda.Tienda.services.User_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        Optional<Users> user = null;
        Map<String, Object> response = new HashMap<>();

        try {
            user = repositorio.findById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (user == null) {
            response.put("Mensaje", "El usuario " + id + " no fue encontrado");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Users>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> save(@Valid @RequestBody Users nuevo, BindingResult result) {
        Users user = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo " + err.getField() + " " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            nuevo.setPassword(encoder.encode(nuevo.getPassword()));
            user = repositorio.save(nuevo);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al registrar en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id, @RequestBody Users data,
            BindingResult result) {
        Optional<Users> actual = repositorio.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (actual == null) {
            response.put("Mensaje", "El usuario " + id + " no fue encontrado");
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
            actual.get().setApellidos(data.getApellidos());
            actual.get().setEmail(data.getEmail());
            actual.get().setPassword(data.getPassword());

            repositorio.save(actual.get());

        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al buscar usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actual.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            repositorio.deleteById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al eliminar usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje", "Registro eliminado");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
