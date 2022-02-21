package com.tienda.Tienda.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "producto")
@EntityListeners(AuditingEntityListener.class)
public class producto implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private double costo_unidad;
    private int stock;
    private Date date_register;

    public producto() {
        nombre = "";
        descripcion = "";
        costo_unidad = 0;
        stock = 0;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
    }

    public Date getDate_register() {
        return date_register;
    }

}
