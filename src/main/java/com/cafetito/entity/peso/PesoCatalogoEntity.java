/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.peso;

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
@Table(name = "catalogo",
        schema = "pesocabal")
public class PesoCatalogoEntity {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", unique = true, nullable = false)
    private int codigo;

    @Column(name = "codigo_catalogo")
    private int codigoCatalogo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "usuario_agrego")
    private String usuarioAgrego;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaAgrego;

    @Column(name = "usuario_modifico")
    private String usuarioModifico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifico")
    private Date fechaModifico;

    public PesoCatalogoEntity() {
    }

    public PesoCatalogoEntity(int codigo) {
        this.codigo = codigo;
    }

    public PesoCatalogoEntity(int codigo, int codigoCatalogo, String nombre, String descripcion, String usuarioAgrego, Date fechaAgrego, String usuarioModifico, Date fechaModifico) {
        this.codigo = codigo;
        this.codigoCatalogo = codigoCatalogo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioAgrego = usuarioAgrego;
        this.fechaAgrego = fechaAgrego;
        this.usuarioModifico = usuarioModifico;
        this.fechaModifico = fechaModifico;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoCatalogo() {
        return codigoCatalogo;
    }

    public void setCodigoCatalogo(int codigoCatalogo) {
        this.codigoCatalogo = codigoCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioAgrego() {
        return usuarioAgrego;
    }

    public void setUsuarioAgrego(String usuarioAgrego) {
        this.usuarioAgrego = usuarioAgrego;
    }

    public Date getFechaAgrego() {
        return fechaAgrego;
    }

    public void setFechaAgrego(Date fechaAgrego) {
        this.fechaAgrego = fechaAgrego;
    }

    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(String usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    public Date getFechaModifico() {
        return fechaModifico;
    }

    public void setFechaModifico(Date fechaModifico) {
        this.fechaModifico = fechaModifico;
    }
    
    
}
