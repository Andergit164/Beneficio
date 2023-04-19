/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.dtos.CuentaDto;
import com.cafetito.entity.CuentaEntity;
import java.util.List;

/**
 *
 * @author Anderson
 */
public interface ICuenta {
    
    String wsStatus();
    
    List<CuentaEntity> listarCuentaAgricultor(String nitAgricultor);
    
    String createCuenta(CuentaDto cuentaDto);
}
