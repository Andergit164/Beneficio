/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.dtos.AgricultorDto;
import com.cafetito.entity.AgricultorEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
public interface IAgricultor {
    
    public abstract List<AgricultorEntity> listaAgricultores();
    
    ResponseEntity<AgricultorEntity> crearAgricultor(AgricultorDto dto);
}
