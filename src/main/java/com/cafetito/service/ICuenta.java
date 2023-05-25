/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.dtos.CuentaDto;
import com.cafetito.dtos.RechazoDto;
import com.cafetito.dtos.UserLoggedDto;
import com.cafetito.entity.CuentaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
public interface ICuenta {
    
    String wsStatus();
    
    List<CuentaEntity> listarCuentaAgricultor(String nitAgricultor);
    
    List<CuentaEntity> listarCuentaAgricultorPorEstado(String nitAgricultor, Integer state);
    
//    String createCuenta(CuentaDto cuentaDto);
    
    ResponseEntity<CuentaEntity> stateCloseAccount(int idCuenta, UserLoggedDto dto);
    
    ResponseEntity<CuentaEntity> stateConfirmedAccount(int idCuenta, UserLoggedDto dto);
    
    Optional<CuentaEntity> showAccount(int idCuenta);
    
    ResponseEntity<CuentaEntity> declineAccount(RechazoDto dto);
}
