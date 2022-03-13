package com.tienda.Tienda.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "producto_insumo")
@EntityListeners(AuditingEntityListener.class)
public class Producto_insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_producto;
    private Integer id_insumo;
    private String descripcion;
    private Integer restar;

    public Producto_insumo() {
        id = 0;
        id_producto = 0;
        id_insumo = 0;
        descripcion = "";
        restar = 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getid() {
        return id;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_insumo(Integer id_insumo) {
        this.id_insumo = id_insumo;
    }

    public Integer getId_insumo() {
        return id_insumo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setRestar(Integer restar) {
        this.restar = restar;
    }

    public Integer getRestar() {
        return restar;
    }

}
