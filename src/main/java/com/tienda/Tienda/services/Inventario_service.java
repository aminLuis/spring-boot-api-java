package com.tienda.Tienda.services;

import com.tienda.Tienda.models.Inventario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Inventario_service extends JpaRepository<Inventario, Integer> {

}
