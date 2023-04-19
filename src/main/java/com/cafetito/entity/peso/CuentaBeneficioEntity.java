/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.entity.peso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;

/**
 *
 * @author Anderson
 */
@Builder
@Entity
@Table(name = "parcialidad", schema = "public")
public class CuentaBeneficioEntity {
    
    private int idCuenta;
    private int idPesaje;
    private String estadoCuenta;

    public CuentaBeneficioEntity() {
    }

    public CuentaBeneficioEntity(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public CuentaBeneficioEntity(int idCuenta, int idPesaje, String estadoCuenta) {
        this.idCuenta = idCuenta;
        this.idPesaje = idPesaje;
        this.estadoCuenta = estadoCuenta;
    }

    @Id
    @Column(name = "id_cuenta")
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Column(name = "id_pesaje")
    public int getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(int idPesaje) {
        this.idPesaje = idPesaje;
    }

    @Column(name = "estado_cuenta")
    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

}
