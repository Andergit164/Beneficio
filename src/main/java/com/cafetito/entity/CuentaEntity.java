/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.EstadosEntity;
import lombok.Builder;

/**
 *
 * @author Anderson
 */
@Builder
@Entity
@Table(name = "cuenta", schema = "public")
public class CuentaEntity implements Serializable{
    
    private int idCuenta;
    private int idPesaje;
    private EstadosEntity idEstado;
    private Date fechaCreacion;
    private Double pesoEnviado;
    private Double pesoTotalObtenido;
    private Double diferenciaTotal; 
    private AgricultorEntity nitAgricultor;

    public CuentaEntity() {
    }

    public CuentaEntity(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    public CuentaEntity(int idCuenta, int idPesaje, EstadosEntity idEstado, Date fechaCreacion, Double pesoEnviado, Double pesoTotalObtenido, Double diferenciaTotal, AgricultorEntity nitAgricultor) {
        this.idCuenta = idCuenta;
        this.idPesaje = idPesaje;
        this.idEstado = idEstado;
        this.fechaCreacion = fechaCreacion;
        this.pesoEnviado = pesoEnviado;
        this.pesoTotalObtenido = pesoTotalObtenido;
        this.diferenciaTotal = diferenciaTotal;
        this.nitAgricultor = nitAgricultor;
    }

    
    @Id
    @Column(name = "id_cuenta", unique=true, nullable=false)
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Column(name = "id_pesaje")
    public int getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion_registro")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "peso_enviado_kg")
    public Double getPesoEnviado() {
        return pesoEnviado;
    }

    public void setPesoEnviado(Double pesoEnviado) {
        this.pesoEnviado = pesoEnviado;
    }

    @Column(name = "peso_total_obtenido_kg")
    public Double getPesoTotalObtenido() {
        return pesoTotalObtenido;
    }

    public void setPesoTotalObtenido(Double pesoTotalObtenido) {
        this.pesoTotalObtenido = pesoTotalObtenido;
    }

    @Column(name = "diferencia_total_kg")
    public Double getDiferenciaTotal() {
        return diferenciaTotal;
    }

    public void setDiferenciaTotal(Double diferenciaTotal) {
        this.diferenciaTotal = diferenciaTotal;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado")
    //@Column(name = "id_estado")
      public EstadosEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosEntity idEstado) {
        this.idEstado = idEstado;
    }

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nit_agricultor")
    //@Column(name = "nit_agricultor")
     public AgricultorEntity getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(AgricultorEntity nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    @Override
    public String toString() {
        return "CuentaEntity{" + "idCuenta=" + idCuenta + ", idPesaje=" + idPesaje + ", idEstado=" + idEstado + ", fechaCreacion=" + fechaCreacion + ", pesoEnviado=" + pesoEnviado + ", pesoTotalObtenido=" + pesoTotalObtenido + ", diferenciaTotal=" + diferenciaTotal + ", nitAgricultor=" + nitAgricultor + '}';
    }

}
