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
    @Column(name = "id_parcialidad")
    private int idParcialidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pesaje")
    private PesajeAgriEntity idPesaje;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transporte")
    private TransporteAgriEntity idTransporte;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transportista")
    private TransportistaAgriEntity idTransportista;
    
    @Column(name = "id_parcialidad_beneficio")
    private int idParcialidadBeneficio;
    
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
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_recepcion_parcialidad")
    private Date fechaRecepcionParcialidad;
    
    public ParcialidadAgriEntity() {
    }

    public ParcialidadAgriEntity(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public ParcialidadAgriEntity(int idParcialidad, PesajeAgriEntity idPesaje, TransporteAgriEntity idTransporte, TransportistaAgriEntity idTransportista, int idParcialidadBeneficio, double pesoParcialidad, String tipoMedida, String usuarioAgrega, Date fechaCreacion, String usuarioModifica, Date fechaModifico, Date fechaRecepcionParcialidad) {
        this.idParcialidad = idParcialidad;
        this.idPesaje = idPesaje;
        this.idTransporte = idTransporte;
        this.idTransportista = idTransportista;
        this.idParcialidadBeneficio = idParcialidadBeneficio;
        this.pesoParcialidad = pesoParcialidad;
        this.tipoMedida = tipoMedida;
        this.usuarioAgrega = usuarioAgrega;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifico = fechaModifico;
        this.fechaRecepcionParcialidad = fechaRecepcionParcialidad;
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

    public TransportistaAgriEntity getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(TransportistaAgriEntity idTransportista) {
        this.idTransportista = idTransportista;
    }

    public int getIdParcialidadBeneficio() {
        return idParcialidadBeneficio;
    }

    public void setIdParcialidadBeneficio(int idParcialidadBeneficio) {
        this.idParcialidadBeneficio = idParcialidadBeneficio;
    }

    public Date getFechaRecepcionParcialidad() {
        return fechaRecepcionParcialidad;
    }

    public void setFechaRecepcionParcialidad(Date fechaRecepcionParcialidad) {
        this.fechaRecepcionParcialidad = fechaRecepcionParcialidad;
    }

}
