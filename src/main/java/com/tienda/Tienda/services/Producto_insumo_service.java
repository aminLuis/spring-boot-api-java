package com.tienda.Tienda.services;

import java.util.List;

import com.tienda.Tienda.models.Producto_insumo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Producto_insumo_service extends JpaRepository<Producto_insumo, Integer> {
    @Query(nativeQuery = true, value = "SELECT *FROM producto_insumo WHERE id_producto=?1")
    List<Producto_insumo> findById_producto(Integer id);
}
