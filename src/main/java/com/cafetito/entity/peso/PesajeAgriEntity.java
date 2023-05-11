/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.peso;

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
@Table(name = "pesaje",
        schema = "agricultor")
public class PesajeAgriEntity implements Serializable {

    @Id
    @Column(name = "id_pesaje", unique = true)
    private int idPesaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nit_agricultor")
    private AgricultorAgriEntity nitAgricultor;

    @Column(name = "id_cuenta")
    private int idCuenta;

    @Column(name = "peso_total_kg")
    private double pesoTotal;

    @Column(name = "total_parcialidades")
    private int totalParcialidades;

    @Column(name = "usuario_agrego")
    private String usuarioAgrega;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "usuario_modifico")
    private String usuarioModifica;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifico")
    private Date fechaModifico;

    public PesajeAgriEntity() {
    }

    public PesajeAgriEntity(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    public PesajeAgriEntity(int idPesaje, AgricultorAgriEntity nitAgricultor, int idCuenta, double pesoTotal, int totalParcialidades, String usuarioAgrega, Date fechaCreacion, String usuarioModifica, Date fechaModifico) {
        this.idPesaje = idPesaje;
        this.nitAgricultor = nitAgricultor;
        this.idCuenta = idCuenta;
        this.pesoTotal = pesoTotal;
        this.totalParcialidades = totalParcialidades;
        this.usuarioAgrega = usuarioAgrega;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifico = fechaModifico;
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

    public int getTotalParcialidades() {
        return totalParcialidades;
    }

    public void setTotalParcialidades(int totalParcialidades) {
        this.totalParcialidades = totalParcialidades;
    }

    public String getUsuarioAgrega() {
        return usuarioAgrega;
    }

    public void setUsuarioAgrega(String usuarioAgrega) {
        this.usuarioAgrega = usuarioAgrega;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaModifico() {
        return fechaModifico;
    }

    public void setFechaModifico(Date fechaModifico) {
        this.fechaModifico = fechaModifico;
    }

}
