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
@Table(name = "parcialidad", schema = "public")
public class ParcialidadEntity implements Serializable{
    
    private int idParcialidad;
    private CuentaEntity idCuenta;
    private TransporteEntity idTransporte;
    private TransportistaEntity idTransportista;
    private Date fechaRecepcionParcialidad;
    private double pesoEnviado;
    private double pesoBascula;
    private double diferenciaPeso;
    private Date fechaPesoBascula;

    public ParcialidadEntity() {
    }
    
    public ParcialidadEntity(int idParcialidad, CuentaEntity idCuenta, TransporteEntity idTransporte, TransportistaEntity idTransportista, Date fechaRecepcionParcialidad, double pesoEnviado, double pesoBascula, double diferenciaPeso, Date fechaPesoBascula) {
        this.idParcialidad = idParcialidad;
        this.idCuenta = idCuenta;
        this.idTransporte = idTransporte;
        this.idTransportista = idTransportista;
        this.fechaRecepcionParcialidad = fechaRecepcionParcialidad;
        this.pesoEnviado = pesoEnviado;
        this.pesoBascula = pesoBascula;
        this.diferenciaPeso = diferenciaPeso;
        this.fechaPesoBascula = fechaPesoBascula;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcialidad")
    public int getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cuenta")
    public CuentaEntity getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(CuentaEntity idCuenta) {
        this.idCuenta = idCuenta;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transporte")
    public TransporteEntity getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(TransporteEntity idTransporte) {
        this.idTransporte = idTransporte;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transportista")
    public TransportistaEntity getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(TransportistaEntity idTransportista) {
        this.idTransportista = idTransportista;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_recepcion_parcialidad")
    public Date getFechaRecepcionParcialidad() {
        return fechaRecepcionParcialidad;
    }

    public void setFechaRecepcionParcialidad(Date fechaRecepcionParcialidad) {
        this.fechaRecepcionParcialidad = fechaRecepcionParcialidad;
    }

    @Column(name = "peso_enviado_kg")
    public double getPesoEnviado() {
        return pesoEnviado;
    }

    public void setPesoEnviado(double pesoEnviado) {
        this.pesoEnviado = pesoEnviado;
    }

    @Column(name = "peso_bascula_kg")
    public double getPesoBascula() {
        return pesoBascula;
    }

    public void setPesoBascula(double pesoBascula) {
        this.pesoBascula = pesoBascula;
    }

    @Column(name = "diferencia_kg")
    public double getDiferenciaPeso() {
        return diferenciaPeso;
    }

    public void setDiferenciaPeso(double diferenciaPeso) {
        this.diferenciaPeso = diferenciaPeso;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_peso")
    public Date getFechaPesoBascula() {
        return fechaPesoBascula;
    }

    public void setFechaPesoBascula(Date fechaPesoBascula) {
        this.fechaPesoBascula = fechaPesoBascula;
    }
    
}
