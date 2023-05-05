/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.TransporteAgriDto;
import com.cafetito.entity.agricultor.TransporteAgriEntity;
import com.cafetito.repository.agricultor.TransporteAgriRepository;
import com.cafetito.service.agricultor.ITransporteAgri;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class TransporteAgriImpl implements ITransporteAgri{
    
    @Autowired
    private TransporteAgriRepository transporte;

    @Override
    public String agregarTransporte(TransporteAgriDto dto) {
        transporte.save(
                TransporteAgriEntity.builder()
                        .placa(dto.getPlaca())
                        .marca(dto.getMarca())
                        .color(dto.getColor())
                        .modelo(dto.getModelo())
                        .activo(true)
                        .fechaCreacion(new Date())
                        .build()
        );
        return null;
    }

    @Override
    public List<TransporteAgriEntity> listarTransporte() {
        return transporte.findAll();
    }
    
}
