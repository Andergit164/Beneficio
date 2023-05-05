/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.TransportistaAgriDto;
import com.cafetito.entity.agricultor.TransporteAgriEntity;
import com.cafetito.entity.agricultor.TransportistaAgriEntity;
import com.cafetito.repository.agricultor.TransportistaAgriRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafetito.service.agricultor.ITransportistaAgri;

/**
 *
 * @author Anderson
 */
@Service
public class TransportistaAgriImpl implements ITransportistaAgri{
    
    @Autowired
    private TransportistaAgriRepository transportista;

    @Override
    public String crearTransportista(TransportistaAgriDto dto) {
       transportista.save(
               TransportistaAgriEntity.builder()
                       .idTransportista(dto.getIdTransportista())
                       .idTransporte(new TransporteAgriEntity(dto.getIdTransporte()))
                       .nombre(dto.getNombre())
                       .fechaNacimiento(dto.getFechaNacimiento())
                       .activo(dto.getActivo())
                       .fechaCreacion(new Date())
                       .build()
       );
       return null;
    }

    @Override
    public List<TransportistaAgriEntity> listarTransportistas() {
        return transportista.findAll();
    }
    

    
    
}
