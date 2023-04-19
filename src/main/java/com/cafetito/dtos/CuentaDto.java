/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.dtos;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Anderson
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto implements Serializable{
    private Integer idCuenta;
    private String nitAgricultor;
    private Integer idPesaje;
    private Integer idEstado;
    private Date fechaCreacion;
    private Double pesoEnviado;
    private Double pesoTotalObtenido;
    private Double diferenciaTotal;
}
