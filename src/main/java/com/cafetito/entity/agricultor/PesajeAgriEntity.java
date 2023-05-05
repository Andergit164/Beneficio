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
@Table(name = "pesaje", schema = "public")
public class PesajeAgriEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pesaje", unique = true, nullable = false)
    private int idPesaje;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nit_agricultor")
    private AgricultorAgriEntity nitAgricultor;
    
    @Column(name = "id_cuenta")
    private int idCuenta;
    
    @Column(name = "peso_total_kg")
    private double pesoTotal;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    public PesajeAgriEntity() {
    }

    public PesajeAgriEntity(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    public PesajeAgriEntity(int idPesaje, AgricultorAgriEntity nitAgricultor, int idCuenta, double pesoTotal, Date fechaCreacion) {
        this.idPesaje = idPesaje;
        this.nitAgricultor = nitAgricultor;
        this.idCuenta = idCuenta;
        this.pesoTotal = pesoTotal;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    public AgricultorAgriEntity getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(AgricultorAgriEntity nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
