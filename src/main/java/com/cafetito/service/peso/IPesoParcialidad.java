/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service.peso;

import com.cafetito.dtos.peso.PesoParcialidadDto;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.peso.PesoParcialidadEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
public interface IPesoParcialidad {
    
    List<PesoParcialidadEntity> listarParcialidades(Integer idCuenta);

    ResponseEntity<PesoParcialidadEntity> actualiarPeso(PesoParcialidadDto dto);
    
    List<ParcialidadEntity> listParcialidadPesadas(Integer idPesaje);
}
