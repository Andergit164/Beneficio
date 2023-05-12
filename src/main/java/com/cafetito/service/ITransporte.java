/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.dtos.TransporteDto;
import com.cafetito.dtos.updateTransDto;
import com.cafetito.entity.TransporteEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;



/**
 *
 * @author Anderson
 */
public interface ITransporte {
    
//    public abstract String saveTransport(TransporteDto transporteDto);
    
    public abstract List<TransporteEntity> listTransport(String nitAgricultor);
    
    ResponseEntity<TransporteEntity> activarInactivarTransporte(String placa, updateTransDto dto);
    
    ResponseEntity<TransporteEntity> deleteTransporte(String placa);
}
