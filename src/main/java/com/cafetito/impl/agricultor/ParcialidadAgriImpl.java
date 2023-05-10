/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.ParcialidadAgriDto;
import com.cafetito.entity.peso.ParcialidadAgriEntity;
import com.cafetito.entity.peso.PesajeAgriEntity;
import com.cafetito.entity.peso.TransporteAgriEntity;
import com.cafetito.repository.peso.ParcialidadAgriRepository;
import com.cafetito.service.agricultor.IParcialidadAgri;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class ParcialidadAgriImpl implements IParcialidadAgri{
    
    @Autowired
    private ParcialidadAgriRepository parcialidad;

    @Override
    public String crearParcialidad(ParcialidadAgriDto dto) {
         parcialidad.save(
                 ParcialidadAgriEntity.builder()
                         .idPesaje(new PesajeAgriEntity(dto.getIdPesaje()))
                         .idTransporte(new TransporteAgriEntity(dto.getIdTransporte()))
                         .pesoParcialidad(dto.getPesoParcialidad())
                         .tipoMedida(dto.getTipoMedida())
                         .fechaCreacion(new Date())
                         //.usuarioAgrega(user.logged)
                         .build()
         );
         return null;
    }

    @Override
    public List<ParcialidadAgriEntity> listarParcialidades(Integer idPesaje) {
        return parcialidad.listarParcialidades(idPesaje);
    }
    
}
