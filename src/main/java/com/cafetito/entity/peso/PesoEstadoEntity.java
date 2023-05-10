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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "estado",
        schema = "pesocabal")
public class PesoEstadoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", unique = true, nullable = false)
    private int idEstado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "detalle")
    private String detalle;

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

    public PesoEstadoEntity() {
    }

    public PesoEstadoEntity(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public PesoEstadoEntity(int idEstado, String nombre, String detalle, String usuarioAgrega, Date fechaCreacion, String usuarioModifica, Date fechaModifico) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.detalle = detalle;
        this.usuarioAgrega = usuarioAgrega;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifico = fechaModifico;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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
