package com.tienda.Tienda.services;

import com.tienda.Tienda.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Cliente_service extends JpaRepository<Cliente, Integer> {

}
