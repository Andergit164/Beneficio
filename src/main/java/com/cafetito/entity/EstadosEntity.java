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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "estados", schema = "public")
public class EstadosEntity implements Serializable{
    
    private int idEstado;
    private String nombre;
    private String detalle;
    private Date fecha;

    public EstadosEntity() {
    }
    
     public EstadosEntity(Integer idEstado) {
         this.idEstado = idEstado;
    }


    public EstadosEntity(int idEstado, String nombre, String detalle, Date fecha) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.detalle = detalle;
        this.fecha = fecha;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "detalle")
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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
        return "EstadoEntity{" + "idEstado=" + idEstado + ", nombre=" + nombre + ", detalle=" + detalle + ", fecha=" + fecha + '}';
    }
    
      
}
