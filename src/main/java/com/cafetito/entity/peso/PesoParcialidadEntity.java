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
@Table(name = "pesoparcialidad",
        schema = "pesocabal")
public class PesoParcialidadEntity implements Serializable {

    @Id
    @Column(name = "id_parcialidad", unique = true, nullable = false)
    private int idParcialidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cuenta")
    private PesoCuentaEntity idCuenta;

    @Column(name = "id_transporte")
    private String idTransporte;

    @Column(name = "id_transportista")
    private String idTransportista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medida_peso")
    private PesoCatalogoEntity medidaPeso;

    @Column(name = "tipo_medida")
    private String tipoMedida;

    @Column(name = "aceptado")
    private boolean aceptado;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "peso_obtenido_kg")
    private Double pesoObtenido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_peso")
    private Date fechaPeso;

    @Column(name = "boleta_generada")
    private boolean boleta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_boleta")
    private Date fechaBoleta;

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

    public PesoParcialidadEntity() {
    }

    public PesoParcialidadEntity(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public PesoParcialidadEntity(int idParcialidad, PesoCuentaEntity idCuenta, String idTransporte, String idTransportista, PesoCatalogoEntity medidaPeso, String tipoMedida, boolean aceptado, String observaciones, Double pesoObtenido, Date fechaPeso, boolean boleta, Date fechaBoleta, String usuarioAgrega, Date fechaCreacion, String usuarioModifica, Date fechaModifico) {
        this.idParcialidad = idParcialidad;
        this.idCuenta = idCuenta;
        this.idTransporte = idTransporte;
        this.idTransportista = idTransportista;
        this.medidaPeso = medidaPeso;
        this.tipoMedida = tipoMedida;
        this.aceptado = aceptado;
        this.observaciones = observaciones;
        this.pesoObtenido = pesoObtenido;
        this.fechaPeso = fechaPeso;
        this.boleta = boleta;
        this.fechaBoleta = fechaBoleta;
        this.usuarioAgrega = usuarioAgrega;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifico = fechaModifico;
    }

    public int getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public PesoCuentaEntity getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(PesoCuentaEntity idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(String idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(String idTransportista) {
        this.idTransportista = idTransportista;
    }

    public PesoCatalogoEntity getMedidaPeso() {
        return medidaPeso;
    }

    public void setMedidaPeso(PesoCatalogoEntity medidaPeso) {
        this.medidaPeso = medidaPeso;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getPesoObtenido() {
        return pesoObtenido;
    }

    public void setPesoObtenido(Double pesoObtenido) {
        this.pesoObtenido = pesoObtenido;
    }

    public Date getFechaPeso() {
        return fechaPeso;
    }

    public void setFechaPeso(Date fechaPeso) {
        this.fechaPeso = fechaPeso;
    }

    public boolean isBoleta() {
        return boleta;
    }

    public void setBoleta(boolean boleta) {
        this.boleta = boleta;
    }

    public Date getFechaBoleta() {
        return fechaBoleta;
    }

    public void setFechaBoleta(Date fechaBoleta) {
        this.fechaBoleta = fechaBoleta;
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
