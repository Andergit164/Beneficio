/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.TransportistaDto;
import com.cafetito.dtos.updateTransDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.TransportistaRepository;
import com.cafetito.service.ITransportista;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class TransportistaImpl implements ITransportista {

    @Autowired
    private TransportistaRepository transportistaRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Override
    public String createCarrier(TransportistaDto dto) {
        transportistaRepository.save(
                TransportistaEntity.builder()
                        .idTransportista(dto.getIdTransportista())
                        .nitAgricultor(new AgricultorEntity(dto.getNitAgricultor()))
                        .nombre(dto.getNombre())
                        .estado("Activo")
                        .fechaCreacion(new Date())
                        .build()
        );

        bitacora.save(
                HistoricoBitacoraEntity.builder()
                        .idRegistro(String.valueOf(dto.getIdTransportista()))
                        .accion("INSERT")
                        .tabla("transportista")
                        .activo(false)
                        .usuarioAgrego("localHost")
                        .fechaAccion(new Date())
                        .build()
        );
        return null;
    }

    @Override
    public List<TransportistaEntity> listCarriers(String nitAgricultor) {
        return transportistaRepository.listCarriersByAgricultor(nitAgricultor);
    }

    @Override
    public TransportistaEntity activarInactivarTransportista(Integer dpi, updateTransDto dto) {
        final TransportistaEntity updateTransportista = transportistaRepository.findById(dpi).orElse(null);
        if (updateTransportista != null) {
            updateTransportista.setActivo(dto.getActivo());
            updateTransportista.setObservaciones(dto.getObservaciones());
            transportistaRepository.save(updateTransportista);

            bitacora.save(
                    HistoricoBitacoraEntity.builder()
                            .idRegistro(String.valueOf(updateTransportista.getIdTransportista()))
                            .accion("UPTDATE")
                            .tabla("transportista")
                            .activo(dto.getActivo())
                            .usuarioAgrego("localHost")
                            .fechaAccion(new Date())
                            .build()
            );
        } else {

        }

        return updateTransportista;
    }

    @Override
    public TransportistaEntity deleteTransportista(Integer dpi) {
          final TransportistaEntity deleteTransportista = transportistaRepository.findById(dpi).orElse(null);
        if (deleteTransportista != null) {
            deleteTransportista.setActivo(false);
            deleteTransportista.setEstado("Inactivo");
            transportistaRepository.save(deleteTransportista);

            bitacora.save(
                    HistoricoBitacoraEntity.builder()
                            .idRegistro(String.valueOf(deleteTransportista.getIdTransportista()))
                            .accion("DELETE")
                            .tabla("transportista")
                            .activo(false)
                            .usuarioAgrego("localHost")
                            .fechaAccion(new Date())
                            .build()
            );
        } else {

        }

        return deleteTransportista;
    }

}
