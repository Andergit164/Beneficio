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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "transporte", schema = "public")
public class TransporteEntity implements Serializable{
    
    private String idTransporte;
    private AgricultorEntity nitAgricultor;
    private boolean activo;
    private String observaciones;
    private String estado;
    private Boolean disponible;
    private Date fechaCreacion;

    public TransporteEntity() {
    }
    
    public TransporteEntity(String idTransporte) {
        this.idTransporte = idTransporte;
    }

    public TransporteEntity(String idTransporte, AgricultorEntity nitAgricultor, boolean activo, String observaciones, String estado, Boolean disponible, Date fechaCreacion) {
        this.idTransporte = idTransporte;
        this.nitAgricultor = nitAgricultor;
        this.activo = activo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.disponible = disponible;
        this.fechaCreacion = fechaCreacion;
    }

    @Id
    @Column(name = "id_transporte", unique=true, nullable=false)
    public String getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(String idTransporte) {
        this.idTransporte = idTransporte;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nit_agricultor")
    public AgricultorEntity getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(AgricultorEntity nitAgricultor) {
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion_registro")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

     @Column(name = "disponible")
    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

}
