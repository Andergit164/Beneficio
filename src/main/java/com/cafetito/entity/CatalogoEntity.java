/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;

/**
 *
 * @author Anderson
 */
@Builder
@Entity
@Table(name = "catalogo", schema = "public")
public class CatalogoEntity {

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

    public CatalogoEntity() {
    }

    public CatalogoEntity(int codigo) {
        this.codigo = codigo;
    }

    public CatalogoEntity(int codigo, int codigoCatalogo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.codigoCatalogo = codigoCatalogo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
    
    

}
