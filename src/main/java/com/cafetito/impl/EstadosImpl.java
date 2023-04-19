/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.entity.EstadosEntity;
import com.cafetito.repository.EstadosRepository;
import com.cafetito.service.IEstados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Anderson
 */
public class EstadosImpl implements IEstados{
    
    @Autowired
    EstadosRepository estadosRepository;

    @Override
    public List<EstadosEntity> listaEstados() {
      return estadosRepository.findAll();
    }
    
}
