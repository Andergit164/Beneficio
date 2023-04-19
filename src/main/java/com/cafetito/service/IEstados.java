/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.entity.EstadosEntity;
import java.util.List;

/**
 *
 * @author Anderson
 */
public interface IEstados {
    
    public abstract List<EstadosEntity> listaEstados();
    
}
