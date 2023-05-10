/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.PesajeAgriDto;
import com.cafetito.entity.peso.AgricultorAgriEntity;
import com.cafetito.entity.peso.PesajeAgriEntity;
import com.cafetito.repository.peso.PesajeAgriRepository;
import com.cafetito.service.agricultor.IPesajeAgri;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class PesajeAgriImpl implements IPesajeAgri{

    @Autowired
    private PesajeAgriRepository pesaje;
            
    @Override
    public String crearPesaje(PesajeAgriDto dto) {
    
        pesaje.save(
                PesajeAgriEntity.builder()
                        .nitAgricultor(new AgricultorAgriEntity(dto.getNitAgricultor()))
                        .idCuenta(dto.getIdCuenta())
                        .pesoTotal(dto.getPesoTotal())
                        .fechaCreacion(new Date())
                        .build()
                        //.usuarioAgrega(userloged)
        );
        return null;
    }

    @Override
    public List<PesajeAgriEntity> listarPesaje() {
        return pesaje.findAll();
    }
    
}
