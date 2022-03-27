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
@Table(name = "producto_insumo")
@EntityListeners(AuditingEntityListener.class)
public class Producto_insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    private Integer id;

    @NotEmpty
    private Integer id_producto;

    @NotEmpty
    private Integer id_insumo;

    @NotEmpty
    private String descripcion;

    @NotEmpty
    private Integer restar;

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
