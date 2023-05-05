/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.agricultor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;

/**
 *
 * @author Anderson
 */
@Builder
@Entity
@Table(name = "agricultor", schema = "public")
public class AgricultorAgriEntity implements Serializable{
    
    @Id
    @Column(name = "nit_agricultor", unique=true, nullable=false)
    private String nitAgricultor;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion_registro")
    private Date fecha;

    public AgricultorAgriEntity() {
    }

    public AgricultorAgriEntity(String nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    public AgricultorAgriEntity(String nitAgricultor, String nombre, String direccion, Date fecha) {
        this.nitAgricultor = nitAgricultor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public String getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(String nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
