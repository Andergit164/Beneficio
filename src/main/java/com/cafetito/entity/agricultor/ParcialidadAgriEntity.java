/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.agricultor;

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
@Table(name = "parcialidad", schema = "public")
public class ParcialidadAgriEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcialidad")
    private int idParcialidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pesaje")
    private PesajeAgriEntity idPesaje;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transporte")
    private TransporteAgriEntity idTransporte;
    
    @Column(name = "peso_parcialidad_kg")
    private double pesoParcialidad;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion_registro")
    private Date fechaCreacion;

    public ParcialidadAgriEntity() {
    }

    public ParcialidadAgriEntity(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public ParcialidadAgriEntity(int idParcialidad, PesajeAgriEntity idPesaje, TransporteAgriEntity idTransporte, double pesoParcialidad, Date fechaCreacion) {
        this.idParcialidad = idParcialidad;
        this.idPesaje = idPesaje;
        this.idTransporte = idTransporte;
        this.pesoParcialidad = pesoParcialidad;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public PesajeAgriEntity getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(PesajeAgriEntity idPesaje) {
        this.idPesaje = idPesaje;
    }

    public TransporteAgriEntity getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(TransporteAgriEntity idTransporte) {
        this.idTransporte = idTransporte;
    }

    public double getPesoParcialidad() {
        return pesoParcialidad;
    }

    public void setPesoParcialidad(double pesoParcialidad) {
        this.pesoParcialidad = pesoParcialidad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
}
