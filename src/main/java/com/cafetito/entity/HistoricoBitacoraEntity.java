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
@Table(name = "historico_bitacora", schema = "public")
public class HistoricoBitacoraEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitcora")
    private int idBitacora;

    @Column(name = "id_registro")
    private String idRegistro;

    @Column(name = "estado_anterior")
    private int estadoAnterior;

    @Column(name = "accion")
    private String accion;

    @Column(name = "tabla")
    private String tabla;

    @Column(name = "estado_nuevo")
    private int estadoNuevo;
    
    @Column(name = "activo")
    private boolean activo;

    @Column(name = "usuario_agrego")
    private String usuarioAgrego;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_accion")
    private Date fechaAccion;

    public HistoricoBitacoraEntity() {
    }

    public HistoricoBitacoraEntity(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public HistoricoBitacoraEntity(int idBitacora, String idRegistro, int estadoAnterior, String accion, String tabla, int estadoNuevo, boolean activo, String usuarioAgrego, Date fechaAccion) {
        this.idBitacora = idBitacora;
        this.idRegistro = idRegistro;
        this.estadoAnterior = estadoAnterior;
        this.accion = accion;
        this.tabla = tabla;
        this.estadoNuevo = estadoNuevo;
        this.activo = activo;
        this.usuarioAgrego = usuarioAgrego;
        this.fechaAccion = fechaAccion;
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(int estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public int getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(int estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUsuarioAgrego() {
        return usuarioAgrego;
    }

    public void setUsuarioAgrego(String usuarioAgrego) {
        this.usuarioAgrego = usuarioAgrego;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    
    
}
