package com.tienda.Tienda.services;

import com.tienda.Tienda.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User_service extends JpaRepository<User, Integer> {
    User findByNombre(String nombre);
}
