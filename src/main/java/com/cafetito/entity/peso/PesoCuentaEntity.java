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
@Table(name = "cuentas",
        schema = "pesocabal")
public class PesoCuentaEntity implements Serializable {

    @Id
    @Column(name = "id_cuenta", unique = true, nullable = false)
    private int idCuenta;

    @Column(name = "id_pesaje")
    private int idPesaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado")
    private PesoEstadoEntity idEstado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medida_peso")
    private PesoCatalogoEntity medidaPeso;

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

    public PesoCuentaEntity() {
    }

    public PesoCuentaEntity(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public PesoCuentaEntity(int idCuenta, int idPesaje, PesoEstadoEntity idEstado, PesoCatalogoEntity medidaPeso, String usuarioAgrega, Date fechaCreacion, String usuarioModifica, Date fechaModifico) {
        this.idCuenta = idCuenta;
        this.idPesaje = idPesaje;
        this.idEstado = idEstado;
        this.medidaPeso = medidaPeso;
        this.usuarioAgrega = usuarioAgrega;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifico = fechaModifico;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    public PesoEstadoEntity getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(PesoEstadoEntity idEstado) {
        this.idEstado = idEstado;
    }

    public PesoCatalogoEntity getMedidaPeso() {
        return medidaPeso;
    }

    public void setMedidaPeso(PesoCatalogoEntity medidaPeso) {
        this.medidaPeso = medidaPeso;
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
