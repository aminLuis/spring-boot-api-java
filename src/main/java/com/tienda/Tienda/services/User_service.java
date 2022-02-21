package com.tienda.Tienda.services;

import com.tienda.Tienda.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User_service extends JpaRepository<Users, Integer> {
    Users findBynombre(String nombre);
}
