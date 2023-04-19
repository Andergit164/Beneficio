/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.util;

import com.cafetito.dtos.AgricultorDto;
import com.cafetito.dtos.CuentaDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.CuentaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anderson
 */
@Component("entityToDtoUtil")
public class EntityToDtoUtil {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public CuentaDto entityToDtoUtil(CuentaEntity cuentaEntity){
        CuentaDto cuentaDto = null;
        if(cuentaEntity != null){
            cuentaDto = modelMapper.map(cuentaDto,CuentaDto.class);
        }
        return cuentaDto;
    }
    
    public AgricultorDto ToDtoUtilAgricultor(AgricultorEntity agricultorEntity){
        AgricultorDto agricultorDto = null;
        if(agricultorEntity != null){
            agricultorDto = modelMapper.map(agricultorDto,AgricultorDto.class);
        }
        return agricultorDto;
    }
}
