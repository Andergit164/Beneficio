/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.peso;

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
@Table(name = "parcialidad", schema = "public")
public class PesoParcialidadEntity {
    
    private int idParcialidad;
    private CuentaBeneficioEntity idCuenta;
    private String idTransporte;
    private int idTransportista;
    private boolean aceptado;
    private String observaciones;
    private double pesoObtenido;
    private Date fechaPeso;
    private boolean boleta;
    private Date fechaBoleta;

    public PesoParcialidadEntity() {
    }

    public PesoParcialidadEntity(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public PesoParcialidadEntity(int idParcialidad, CuentaBeneficioEntity idCuenta, String idTransporte, int idTransportista, boolean aceptado, String observaciones, double pesoObtenido, Date fechaPeso, boolean boleta, Date fechaBoleta) {
        this.idParcialidad = idParcialidad;
        this.idCuenta = idCuenta;
        this.idTransporte = idTransporte;
        this.idTransportista = idTransportista;
        this.aceptado = aceptado;
        this.observaciones = observaciones;
        this.pesoObtenido = pesoObtenido;
        this.fechaPeso = fechaPeso;
        this.boleta = boleta;
        this.fechaBoleta = fechaBoleta;
    }

    @Id
    @Column(name = "id_parcialidad")
    public int getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cuenta")
    public CuentaBeneficioEntity getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(CuentaBeneficioEntity idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Column(name = "id_transporte")
    public String getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(String idTransporte) {
        this.idTransporte = idTransporte;
    }

    @Column(name = "id_transportista")
    public int getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(int idTransportista) {
        this.idTransportista = idTransportista;
    }

    @Column(name = "aceptado")
    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    @Column(name = "observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name = "peso_obtenido_kg")
    public double getPesoObtenido() {
        return pesoObtenido;
    }

    public void setPesoObtenido(double pesoObtenido) {
        this.pesoObtenido = pesoObtenido;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_peso")
    public Date getFechaPeso() {
        return fechaPeso;
    }

    public void setFechaPeso(Date fechaPeso) {
        this.fechaPeso = fechaPeso;
    }

    @Column(name = "boleta_generada")
    public boolean isBoleta() {
        return boleta;
    }

    public void setBoleta(boolean boleta) {
        this.boleta = boleta;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_boleta")
    public Date getFechaBoleta() {
        return fechaBoleta;
    }

    public void setFechaBoleta(Date fechaBoleta) {
        this.fechaBoleta = fechaBoleta;
    }
    
    
}
