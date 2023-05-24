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
public class CuentaEntity implements Serializable {

    private int idCuenta;
    private AgricultorEntity nitAgricultor;
    private int idPesaje;
    private EstadosEntity idEstado;
    private Date fechaCreacion;
    private Double pesoEnviado;
    private Double pesoTotalObtenido;
    private Double diferenciaTotal;
    private String comentario;
    private int totalParcialidades;
    private Boolean tolerancia;
    private String resultadoTolerancia;
    private CatalogoEntity medidaPeso;

    public CuentaEntity() {
    }

    public CuentaEntity(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public CuentaEntity(int idCuenta, AgricultorEntity nitAgricultor, int idPesaje, EstadosEntity idEstado, Date fechaCreacion, Double pesoEnviado, Double pesoTotalObtenido, Double diferenciaTotal, String comentario, int totalParcialidades, Boolean tolerancia, String resultadoTolerancia, CatalogoEntity medidaPeso) {
        this.idCuenta = idCuenta;
        this.nitAgricultor = nitAgricultor;
        this.idPesaje = idPesaje;
        this.idEstado = idEstado;
        this.fechaCreacion = fechaCreacion;
        this.pesoEnviado = pesoEnviado;
        this.pesoTotalObtenido = pesoTotalObtenido;
        this.diferenciaTotal = diferenciaTotal;
        this.comentario = comentario;
        this.totalParcialidades = totalParcialidades;
        this.tolerancia = tolerancia;
        this.resultadoTolerancia = resultadoTolerancia;
        this.medidaPeso = medidaPeso;
    }

    @Id
    @Column(name = "id_cuenta", unique = true, nullable = false)
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

    @Column(name = "peso_enviado")
    public Double getPesoEnviado() {
        return pesoEnviado;
    }

    public void setPesoEnviado(Double pesoEnviado) {
        this.pesoEnviado = pesoEnviado;
    }

    @Column(name = "peso_total_obtenido")
    public Double getPesoTotalObtenido() {
        return pesoTotalObtenido;
    }

    public void setPesoTotalObtenido(Double pesoTotalObtenido) {
        this.pesoTotalObtenido = pesoTotalObtenido;
    }

    @Column(name = "diferencia_total")
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

    @Column(name = "comentario")
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Column(name = "total_parcialidades")
    public int getTotalParcialidades() {
        return totalParcialidades;
    }

    public void setTotalParcialidades(int totalParcialidades) {
        this.totalParcialidades = totalParcialidades;
    }

    @Column(name = "tolerancia")
    public Boolean getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Boolean tolerancia) {
        this.tolerancia = tolerancia;
    }

    @Column(name = "resultado_tolerancia")
    public String getResultadoTolerancia() {
        return resultadoTolerancia;
    }

    public void setResultadoTolerancia(String resultadoTolerancia) {
        this.resultadoTolerancia = resultadoTolerancia;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medida_peso")
    public CatalogoEntity getMedidaPeso() {
        return medidaPeso;
    }

    public void setMedidaPeso(CatalogoEntity medidaPeso) {
        this.medidaPeso = medidaPeso;
    }
    
    @Override
    public String toString() {
        return "CuentaEntity{" + "idCuenta=" + idCuenta + ", idPesaje=" + idPesaje + ", idEstado=" + idEstado + ", fechaCreacion=" + fechaCreacion + ", pesoEnviado=" + pesoEnviado + ", pesoTotalObtenido=" + pesoTotalObtenido + ", diferenciaTotal=" + diferenciaTotal + ", nitAgricultor=" + nitAgricultor + '}';
    }

}
