package com.tienda.Tienda.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "insumo")
@EntityListeners(AuditingEntityListener.class)
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private double costo_unidad;
    private Integer stock;
    private Integer id_inventario;
    private String inventario;

    public Insumo() {
        id = 0;
        nombre = "";
        descripcion = "";
        costo_unidad = 0;
        stock = 0;
        id_inventario = 0;
        inventario = "";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descrString) {
        this.descripcion = descrString;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCosto_unidad(double costo_unidad) {
        this.costo_unidad = costo_unidad;
    }

    public double getCosto_unidad() {
        return costo_unidad;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setId_inventario(Integer id_inventario) {
        this.id_inventario = id_inventario;
    }

    public Integer getId_inventario() {
        return id_inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getInventario() {
        return inventario;
    }

}
