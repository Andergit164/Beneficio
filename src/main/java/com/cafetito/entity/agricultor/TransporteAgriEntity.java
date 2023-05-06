/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.agricultor;

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
@Table(name = "transporteAgri")
public class TransporteAgriEntity {
    
    @Id
    @Column(name = "placa", unique=true)
    private String placa;
    
    @Column(name = "marca")
    private String marca;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "modelo")
    private int modelo;
    
    @Column(name = "activo")
    private boolean activo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion_registro")
    private Date fechaCreacion;

    public TransporteAgriEntity() {
    }
    

    public TransporteAgriEntity(String placa) {
        this.placa = placa;
    }

    public TransporteAgriEntity(String placa, String marca, String color, int modelo, boolean activo, Date fechaCreacion) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.modelo = modelo;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
}
