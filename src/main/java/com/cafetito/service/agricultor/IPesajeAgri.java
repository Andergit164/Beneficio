/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service.agricultor;

import com.cafetito.dtos.agricultor.PesajeAgriDto;
import com.cafetito.entity.peso.PesajeAgriEntity;
import java.util.List;



/**
 *
 * @author Anderson
 */
public interface IPesajeAgri {
    
    String crearPesaje(PesajeAgriDto dto);
    
    List<PesajeAgriEntity> listarPesaje();
}
