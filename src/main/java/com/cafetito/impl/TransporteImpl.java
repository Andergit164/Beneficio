/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.TransporteDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.TransporteRepository;
import com.cafetito.service.ITransporte;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class TransporteImpl implements ITransporte {

    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Override
    public String saveTransport(TransporteDto Dto) {
        transporteRepository.save(
                TransporteEntity.builder()
                        .idTransporte(Dto.getIdTransporte())
                        .nitAgricultor(new AgricultorEntity(Dto.getNitAgricultor()))
                        .observaciones(Dto.getObservaciones())
                        .fechaCreacion(new Date())
                        .build()
        );

        bitacora.save(
                HistoricoBitacoraEntity.builder()
                        .idRegistro(Dto.getIdTransporte())
                        .accion("INSERT")
                        .tabla("transporte")
                        .activo(false)
                        .usuarioAgrego("localHost")
                        .fechaAccion(new Date())
                        .build()
        );
        return null;
    }

    @Override
    public List<TransporteEntity> listTransport(String nitAgricultor) {
        return transporteRepository.listTransport(nitAgricultor);
    }

}
