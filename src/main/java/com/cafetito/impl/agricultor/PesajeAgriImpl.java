/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.PesajeAgriDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.CatalogoEntity;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.EstadosEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.peso.AgricultorAgriEntity;
import com.cafetito.entity.peso.CatalogoAgriEntity;
import com.cafetito.entity.peso.PesajeAgriEntity;
import com.cafetito.repository.CuentaRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.peso.PesajeAgriRepository;
import com.cafetito.service.agricultor.IPesajeAgri;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class PesajeAgriImpl implements IPesajeAgri{

    @Autowired
    private PesajeAgriRepository pesaje;
    
    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Autowired
    private HistoricoBitacoraRepository bitacora;
    
    Integer idCuenta;
    Integer idPesaje;
            
    @Override
    public Boolean crearPesaje(PesajeAgriDto dto) {
    
        this.idCuenta = getIdCuenta();
        this.idPesaje = getIdPesaje();
        //Metodo utilizado para crear el pesaje en el Agricultor
        pesaje.save(
                PesajeAgriEntity.builder()
                        .idPesaje(this.idPesaje)
                        .nitAgricultor(new AgricultorAgriEntity(dto.getNitAgricultor()))
                        .idCuenta(this.idCuenta)
                        .fechaCreacion(new Date())
                        .medidaPeso(new CatalogoAgriEntity(dto.getCodigoPeso()))
                        .usuarioAgrega(dto.getUsuarioAgrego())
                        .build()
                        
        ); 
        
        //Metodo utilizado para crear la cuenta en el Beneficio
        final CuentaEntity account = cuentaRepository.save(
                CuentaEntity.builder()
                        .idCuenta(this.idCuenta)
                        .nitAgricultor(new AgricultorEntity(dto.getNitAgricultor()))
                        .idPesaje(this.idPesaje)
                        .idEstado(new EstadosEntity(1))
                        .medidaPeso(new CatalogoEntity(dto.getCodigoPeso()))
                        .fechaCreacion(new Date())
                        .build()
        );
        
        //Metodo utilizado para guardar en bitacora la cuenta creada, en el Beneficio
        bitacora.save(
                HistoricoBitacoraEntity.builder()
                        .idRegistro(String.valueOf(this.idCuenta))
                        .accion("INSERT")
                        .tabla("cuenta")
                        .estadoAnterior(0)
                        .estadoNuevo(1)
                        .usuarioAgrego(dto.getUsuarioAgrego())
                        .fechaAccion(new Date())
                        .data(new Gson().toJson(account))
                        .build()
        );
        
        return true;
    }

    @Override
    public List<PesajeAgriEntity> listarPesaje() {
        return pesaje.findAll();
    }
    
    /*
    * Metodo Utilizado para obtener el id de la cuenta en el Beneficio
    */
    public Integer getIdCuenta() {
        return cuentaRepository.requestNexVal();
    }
    
    /*
    * Metodo Utilizado para obtener el id del pesaje en el Agricultor
    */
     public Integer getIdPesaje() {
        Integer id;
        id = pesaje.requestNexValPesaje();
        return id;
    }
    
}
