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
public class ParcialidadDto implements Serializable{
    
    private Integer idParcialidad;
    private Integer idCuenta;
    private String idTransporte;
    private Integer idTransportista;
    private Date fechaRecepcionParcialidad;
    private Double pesoEnviado;
    private Double pesoBascula;
    private Double diferenciaPeso;
    private Date fechaPesoBascula;
    
}
