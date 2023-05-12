/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service;

import com.cafetito.dtos.TransportistaDto;
import com.cafetito.dtos.updateTransDto;
import com.cafetito.entity.TransportistaEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
public interface ITransportista {
    
//    public abstract String createCarrier(TransportistaDto dto);
    
    public abstract List<TransportistaEntity> listCarriers(String nitAgricultor);
    
    ResponseEntity<TransportistaEntity> activarInactivarTransportista(Integer dpi, updateTransDto dto);
    
    ResponseEntity<TransportistaEntity> deleteTransportista(Integer dpi);
    
}
