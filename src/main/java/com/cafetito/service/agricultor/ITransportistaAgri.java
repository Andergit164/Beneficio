/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service.agricultor;

import com.cafetito.dtos.agricultor.TransportistaAgriDto;
import com.cafetito.entity.peso.TransportistaAgriEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
public interface ITransportistaAgri {
    
    public abstract ResponseEntity<TransportistaAgriEntity> crearTransportista(TransportistaAgriDto dto);
    public abstract List <TransportistaAgriEntity> listarTransportistas();
}
