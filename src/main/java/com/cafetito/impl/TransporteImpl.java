/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.TransporteDto;
import com.cafetito.dtos.updateTransDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.TransporteRepository;
import com.cafetito.service.ITransporte;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @Override
//    public String saveTransport(TransporteDto Dto) {
//        transporteRepository.save(
//                TransporteEntity.builder()
//                        .idTransporte(Dto.getIdTransporte())
//                        .nitAgricultor(new AgricultorEntity(Dto.getNitAgricultor()))
//                        .fechaCreacion(new Date())
//                        .estado("Activo")
//                        .build()
//        );
//
//        bitacora.save(
//                HistoricoBitacoraEntity.builder()
//                        .idRegistro(Dto.getIdTransporte())
//                        .accion("INSERT")
//                        .tabla("transporte")
//                        .activo(false)
//                        .usuarioAgrego("localHost")
//                        .fechaAccion(new Date())
//                        .build()
//        );
//        return null;
//    }
    @Override
    public List<TransporteEntity> listTransport(String nitAgricultor) {
        return transporteRepository.listTransport(nitAgricultor);
    }

    @Override
    public ResponseEntity<TransporteEntity> activarInactivarTransporte(String placa, updateTransDto dto) {
        final TransporteEntity updateTransporte = transporteRepository.findById(placa).orElse(null);
        String respuesta;
        
        if(updateTransporte.isActivo()){
            respuesta = "Activo";
        }else{
            respuesta = "Inactivo";
        }
        
        if (updateTransporte != null) {
            if (updateTransporte.isActivo() != dto.getActivo()) {

                updateTransporte.setActivo(dto.getActivo());
                updateTransporte.setObservaciones(dto.getObservaciones());
                transporteRepository.save(updateTransporte);

                bitacora.save(
                        HistoricoBitacoraEntity.builder()
                                .idRegistro(updateTransporte.getIdTransporte())
                                .accion("UPDATE")
                                .tabla("transporte")
                                .activo(dto.getActivo())
                                .usuarioAgrego(dto.getUsuarioModifico())
                                .fechaAccion(new Date())
                                .build()
                );
            } else {
                return new ResponseEntity("El transporte ya se encuentra : '" + respuesta + "' ", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("No se encontro información para la placa: " + placa, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Transporte actualizado correctamente", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransporteEntity> deleteTransporte(String placa) {
        final TransporteEntity deleteTransporte = transporteRepository.findById(placa).orElse(null);
        if (deleteTransporte != null) {
            deleteTransporte.setActivo(false);
            deleteTransporte.setEstado("Inactivo");
            transporteRepository.save(deleteTransporte);

            bitacora.save(
                    HistoricoBitacoraEntity.builder()
                            .idRegistro(placa)
                            .accion("DELETE")
                            .tabla("transporte")
                            .activo(false)
                            .usuarioAgrego("localHost")
                            .fechaAccion(new Date())
                            .build()
            );
        } else {
            return new ResponseEntity("No se encontro información para la placa: " + placa, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Transporte eliminado correctamente.", HttpStatus.OK);
    }

}
