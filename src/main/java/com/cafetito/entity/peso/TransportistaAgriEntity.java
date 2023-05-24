/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.peso;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "transportista",
        schema = "agricultor")
public class TransportistaAgriEntity {

    @Id
    @Column(name = "id_transportista", unique = true)
    private String idTransportista;

    @Column(name = "id_pesaje")
    private int idPesaje;

    @Column(name = "nombre")
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "disponible")
    private Boolean disponible;

    @Column(name = "tipo_licencia")
    private String tipoLicencia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_licencia")
    private Date fechaLicencia;

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

    public TransportistaAgriEntity() {
    }

    public TransportistaAgriEntity(String idTransportista) {
        this.idTransportista = idTransportista;
    }

    public TransportistaAgriEntity(String idTransportista, int idPesaje, String nombre, Date fechaNacimiento, boolean activo, Boolean disponible, String tipoLicencia, Date fechaLicencia, String usuarioAgrega, Date fechaCreacion, String usuarioModifica, Date fechaModifico) {
        this.idTransportista = idTransportista;
        this.idPesaje = idPesaje;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.disponible = disponible;
        this.tipoLicencia = tipoLicencia;
        this.fechaLicencia = fechaLicencia;
        this.usuarioAgrega = usuarioAgrega;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifico = fechaModifico;
    }

    public String getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(String idTransportista) {
        this.idTransportista = idTransportista;
    }

    public int getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public Date getFechaLicencia() {
        return fechaLicencia;
    }

    public void setFechaLicencia(Date fechaLicencia) {
        this.fechaLicencia = fechaLicencia;
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
