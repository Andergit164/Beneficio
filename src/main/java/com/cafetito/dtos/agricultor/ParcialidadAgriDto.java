/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.dtos.agricultor;

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
public class ParcialidadAgriDto implements Serializable{
    
    private Integer idParcialidad;
    private Integer idPesaje;
    private String idTransporte;
    private Double pesoParcialidad;
    private Date fechaCreacion;
}