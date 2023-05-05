/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.agricultor;

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
@Table(name = "transportista", schema = "public")
public class TransportistaAgriEntity {
    
    @Id
    @Column(name = "id_transportista", unique=true)
    private int idTransportista;
  
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transporte")
    private TransporteAgriEntity idTransporte;  
    
    @Column(name = "nombre")
    private String nombre;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    
    @Column(name = "activo")
    private boolean activo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion_registro")
    private Date fechaCreacion;

    public TransportistaAgriEntity() {
    }

    
    public TransportistaAgriEntity(int idTransportista) {
        this.idTransportista = idTransportista;
    }


    public TransportistaAgriEntity(int idTransportista, TransporteAgriEntity idTransporte, String nombre, Date fechaNacimiento, boolean activo, Date fechaCreacion) {
        this.idTransportista = idTransportista;
        this.idTransporte = idTransporte;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(int idTransportista) {
        this.idTransportista = idTransportista;
    }

    public TransporteAgriEntity getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(TransporteAgriEntity idTransporte) {
        this.idTransporte = idTransporte;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
}
