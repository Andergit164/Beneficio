/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.dtos.ParcialidadDto;
import com.cafetito.entity.ParcialidadEntity;
import java.util.List;

/**
 *
 * @author Anderson
 */
public interface IParcialidad {
    
    List<ParcialidadEntity> listarParcialidades(Integer idCuenta);
    
//    String createParcialidad(ParcialidadDto Dto);
    
}
