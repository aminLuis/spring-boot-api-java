package com.tienda.Tienda.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.Tienda.models.*;

public interface Producto_service extends JpaRepository<producto, Integer> {

}
