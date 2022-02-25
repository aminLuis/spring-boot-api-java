package com.tienda.Tienda.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long cedula;
    private String nombre;
    private String apellidos;
    private String latitud;
    private String longitud;
    private long telefono;

    public Cliente() {
        id = 0;
        cedula = 0;
        nombre = "";
        apellidos = "";
        latitud = "";
        longitud = "";
        telefono = 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public long getCedula() {
        return cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getTelefono() {
        return telefono;
    }

}
