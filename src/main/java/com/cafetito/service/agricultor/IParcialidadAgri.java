/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service.agricultor;

import com.cafetito.dtos.agricultor.ParcialidadAgriDto;
import com.cafetito.entity.peso.ParcialidadAgriEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
public interface IParcialidadAgri {
    
    public abstract ResponseEntity<ParcialidadAgriEntity> crearParcialidad(ParcialidadAgriDto dto);
    
    List<ParcialidadAgriEntity> listarParcialidades(Integer idPesaje);
}
