/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.ParcialidadDto;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.ParcialidadRepository;
import com.cafetito.service.IParcialidad;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Anderson
 */
@Service
@Slf4j
public class ParcialidadImpl implements IParcialidad {

    @Autowired
    private ParcialidadRepository parcialidadRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Override
    public List<ParcialidadEntity> listarParcialidades(Integer idCuenta) {
        return parcialidadRepository.listParcialidad(idCuenta);
    }

//    Integer id;
//
//    @Override
//    public String createParcialidad(ParcialidadDto dto) {
//        this.id = getId();
//        parcialidadRepository.save(
//                ParcialidadEntity.builder()
//                        .idParcialidad(this.id)
//                        .idCuenta(new CuentaEntity(dto.getIdCuenta()))
//                        .idTransporte(new TransporteEntity(dto.getIdTransporte()))
//                        .idTransportista(new TransportistaEntity(dto.getIdTransportista()))
//                        .pesoEnviado(dto.getPesoEnviado()).recibido("Espera recepción")
//                        .build()
//        );
//
//        bitacora.save(
//                HistoricoBitacoraEntity.builder()
//                        .idRegistro(String.valueOf(this.id))
//                        .accion("INSERT")
//                        .tabla("parcialidad")
//                        .activo(false)
//                        .usuarioAgrego("localHost")
//                        .fechaAccion(new Date())
//                        .build() 
//        );
//        return null;
//    }
//
//    public Integer getId() {
//        Integer id;
//        id = parcialidadRepository.requestNexVal();
//        return id;
//    }

}
