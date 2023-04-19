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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "transportista", schema = "public")
public class TransportistaEntity implements Serializable{

    private int idTransportista;
    private AgricultorEntity nitAgricultor;
    private boolean activo;
    private String observaciones;
    private Date fechaCreacion;

    public TransportistaEntity() {
    }

    public TransportistaEntity(Integer idTransportista) {
        this.idTransportista = idTransportista;
    }

    public TransportistaEntity(int idTransportista, AgricultorEntity nitAgricultor, boolean activo, String observaciones, Date fechaCreacion) {
        this.idTransportista = idTransportista;
        this.nitAgricultor = nitAgricultor;
        this.activo = activo;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
    }

    @Id
    @Column(name = "id_transportista", unique=true, nullable=false)
    public int getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(int idTransportista) {
        this.idTransportista = idTransportista;
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

}
