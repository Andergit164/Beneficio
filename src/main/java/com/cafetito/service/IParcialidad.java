/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.dtos.ParcialidadDto;
import com.cafetito.dtos.updateParcialidadDto;
import com.cafetito.entity.ParcialidadEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
public interface IParcialidad {
    
    List<ParcialidadEntity> listarParcialidades(Integer idCuenta);
    
     ResponseEntity<ParcialidadEntity> ingresoParcialidad(updateParcialidadDto dto);
    
//    String createParcialidad(ParcialidadDto Dto);
    
}
