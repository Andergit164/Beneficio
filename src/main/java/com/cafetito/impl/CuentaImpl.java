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
import com.cafetito.repository.CuentaRepository;
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
public class CuentaImpl implements ICuenta{
    
    @Autowired
    //@Qualifier("cuentaRepository")
    private CuentaRepository cuentaRepository;
   
    
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

     /**
     * @param dto
     * @param cuentaDto
     * @return  Servicio para crear una nueva cuenta.
     */
    @Override
    public String createCuenta(CuentaDto dto) {
        cuentaRepository.save(
            CuentaEntity.builder()
                .idCuenta(getId())
                .nitAgricultor(new AgricultorEntity(dto.getNitAgricultor()))
                .idPesaje(dto.getIdPesaje())
                .idEstado(new EstadosEntity(1))
                .fechaCreacion(new Date())
                .pesoEnviado(dto.getPesoEnviado())
                .build()
        );
        return null;
    }
    
    public Integer getId(){
        Integer Id;
        Id = cuentaRepository.requestNexVal();
        return Id;
    }
    
}
