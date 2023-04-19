/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity;

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
public class AgricultorEntity implements Serializable{
    
    private String nitAgricultor;
    private String nombre;
    private boolean activo;
    private String observaciones;
    private Date fecha;

     public AgricultorEntity() {
    }

    public AgricultorEntity(String nitAgricultor) {
       this.nitAgricultor = nitAgricultor;
    }

    public AgricultorEntity(String nitAgricultor, String nombre, boolean activo, String observaciones, Date fecha) {
        this.nitAgricultor = nitAgricultor;
        this.nombre = nombre;
        this.activo = activo;
        this.observaciones = observaciones;
        this.fecha = fecha;
    }
    
    @Id
    @Column(name = "nit_agricultor", unique=true, nullable=false)
    public String getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(String nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    @Column(name = "activo")
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Column(name = "observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion_registro")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "AgricultorEntity{" + "nitAgricultor=" + nitAgricultor + ", activo=" + activo + ", observaciones=" + observaciones + ", nombre=" + nombre + ", fecha=" + fecha + '}';
    }

   
}
