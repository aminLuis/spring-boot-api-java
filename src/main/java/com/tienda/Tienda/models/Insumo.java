package com.tienda.Tienda.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "insumo")
@EntityListeners(AuditingEntityListener.class)
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    private Integer id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String descripcion;

    @NotEmpty
    private double costo_unidad;

    @NotEmpty
    private Integer stock;

    @NotEmpty
    private Integer id_inventario;

    @NotEmpty
    private String inventario;

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
