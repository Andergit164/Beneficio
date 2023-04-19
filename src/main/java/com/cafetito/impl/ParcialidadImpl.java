/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.ParcialidadDto;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.repository.ParcialidadRepository;
import com.cafetito.service.IParcialidad;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class ParcialidadImpl implements IParcialidad{
    
    @Autowired
    private ParcialidadRepository parcialidadRepository;

    @Override
    public List<ParcialidadEntity> listarParcialidades(Integer idCuenta) {
        return parcialidadRepository.listParcialidad(idCuenta);
    }

    @Override
    public String createParcialidad(ParcialidadDto dto) {
       parcialidadRepository.save(
               ParcialidadEntity.builder()
                       .idCuenta(new CuentaEntity(dto.getIdCuenta()))
                       .idTransporte(new TransporteEntity(dto.getIdTransporte()))
                       .idTransportista(new TransportistaEntity(dto.getIdTransportista()))
                       .fechaRecepcionParcialidad(new Date())
                       .pesoEnviado(dto.getPesoEnviado())
                       .build()
       );
       return null;
    }
    
}
