/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.ParcialidadDto;
import com.cafetito.dtos.updateParcialidadDto;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.EstadosEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.repository.CuentaRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.ParcialidadRepository;
import com.cafetito.service.IParcialidad;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<ParcialidadEntity> listarParcialidades(Integer idCuenta) {
        return parcialidadRepository.listParcialidad(idCuenta);
    }

    @Override
    public ResponseEntity<ParcialidadEntity> ingresoParcialidad(updateParcialidadDto dto) {
        final CuentaEntity count = cuentaRepository.findById(dto.getIdCuenta()).orElse(null);
        final ParcialidadEntity part = parcialidadRepository.findById(dto.getIdParcialidad()).orElse(null);

        if (part != null) {
            if (part.isValido() == false) {
                if (part.getIdTransporte().isActivo() == true) {
                    if (part.getIdTransportista().isActivo() == true) {

                        part.setValido(true);
                        part.setRecibido("Ingresado");
                        part.setFechaRecepcionParcialidad(new Date());
                        parcialidadRepository.save(part);

                        bitacora.save(
                                HistoricoBitacoraEntity.builder()
                                        .idRegistro(String.valueOf(dto.getIdParcialidad()))
                                        .accion("UPDATE")
                                        .tabla("parcialidad")
                                        .activo(true)
                                        .usuarioAgrego(dto.getUsuarioModifico())
                                        .fechaAccion(new Date())
                                        .build()
                        );

                        if (count.getIdEstado().getIdEstado() == 1) {
                            count.setIdEstado(new EstadosEntity(2));
                            cuentaRepository.save(count);

                            bitacora.save(
                                    HistoricoBitacoraEntity.builder()
                                            .idRegistro(String.valueOf(dto.getIdParcialidad()))
                                            .accion("UPDATE")
                                            .tabla("cuenta")
                                            .estadoAnterior(1)
                                            .estadoNuevo(2)
                                            .usuarioAgrego("localHost")
                                            .fechaAccion(new Date())
                                            .build()
                            );
                        }
                    } else {
                        return new ResponseEntity("El transportista: " + part.getIdTransportista() + "No se cuentra activo",
                                HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("El transporte: " + part.getIdTransporte() + "No se cuentra activo",
                            HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("La parcialidad ya fue recibida: " + dto.getIdParcialidad(),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("No se encontro la parcialidad ingresada",
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity("Parcialida Recibida", 
                HttpStatus.OK);
    }
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

