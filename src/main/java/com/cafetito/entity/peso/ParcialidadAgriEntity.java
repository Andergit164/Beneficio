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
@Table(name = "parcialidad",
        schema = "agricultor")
public class ParcialidadAgriEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcialidad")
    private int idParcialidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pesaje")
    private PesajeAgriEntity idPesaje;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transporte")
    private TransporteAgriEntity idTransporte;
    
    @Column(name = "peso_parcialidad_kg")
    private double pesoParcialidad;
    
    @Column(name = "tipo_medida")
    private String tipoMedida;
    
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

    public ParcialidadAgriEntity() {
    }

    public ParcialidadAgriEntity(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public ParcialidadAgriEntity(int idParcialidad, PesajeAgriEntity idPesaje, TransporteAgriEntity idTransporte, double pesoParcialidad, String tipoMedida, String usuarioAgrega, Date fechaCreacion, String usuarioModifica, Date fechaModifico) {
        this.idParcialidad = idParcialidad;
        this.idPesaje = idPesaje;
        this.idTransporte = idTransporte;
        this.pesoParcialidad = pesoParcialidad;
        this.tipoMedida = tipoMedida;
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

    public PesajeAgriEntity getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(PesajeAgriEntity idPesaje) {
        this.idPesaje = idPesaje;
    }

    public TransporteAgriEntity getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(TransporteAgriEntity idTransporte) {
        this.idTransporte = idTransporte;
    }

    public double getPesoParcialidad() {
        return pesoParcialidad;
    }

    public void setPesoParcialidad(double pesoParcialidad) {
        this.pesoParcialidad = pesoParcialidad;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
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