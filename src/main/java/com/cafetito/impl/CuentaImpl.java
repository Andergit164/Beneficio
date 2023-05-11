/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.CuentaDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.EstadosEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.repository.CuentaRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.service.ICuenta;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class CuentaImpl implements ICuenta {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;
    
    Integer id;

    /**
     * @param nitAgricultor
     * @return data Servicio para obtener todas las cuentas de un agricultor.
     */
    @Override
    public List<CuentaEntity> listarCuentaAgricultor(String nitAgricultor) {
        return cuentaRepository.listarCuentas(nitAgricultor);
    }

    /**
     * @return Servicio para obtener la respuesta del WS.
     */
    @Override
    public String wsStatus() {
        return "Activo";
    }

    public Integer getId() {
        return cuentaRepository.requestNexVal();
    }

    @Override
    public CuentaEntity actualizarEstado(int state, int idCuenta) {
        final CuentaEntity updateState = cuentaRepository.findById(idCuenta).orElse(null);
        updateState.setIdEstado(new EstadosEntity(state));
        cuentaRepository.save(updateState);
        return null;
    }
    
    
//
//    @Override
//    public String createCuenta(CuentaDto dto) {
//        this.id = getId();
//        cuentaRepository.save(
//                CuentaEntity.builder()
//                        .idCuenta(this.id)
//                        .nitAgricultor(new AgricultorEntity(dto.getNitAgricultor()))
//                        .idPesaje(dto.getIdPesaje())
//                        .idEstado(new EstadosEntity(1))
//                        .fechaCreacion(new Date())
//                        .pesoEnviado(dto.getPesoEnviado())
//                        .build()
//        );
//        
//         bitacora.save(
//                HistoricoBitacoraEntity.builder()
//                        .idRegistro(String.valueOf(this.id))
//                        .accion("INSERT")
//                        .tabla("cuenta")
//                        .estadoAnterior(0)
//                        .estadoNuevo(1)
//                        .usuarioAgrego("localHost")
//                        .fechaAccion(new Date())
//                        .build()
//        );
//
//        return null;
//    }
}
