/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service.agricultor;

import com.cafetito.dtos.agricultor.TransporteAgriDto;
import com.cafetito.entity.peso.TransporteAgriEntity;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anderson
 */
public interface ITransporteAgri {
    
    public abstract String agregarTransporte(TransporteAgriDto dto);
    public abstract List <TransporteAgriEntity> listarTransporte();
}
