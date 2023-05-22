/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.AgricultorDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.repository.AgricultorRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.service.IAgricultor;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */


@Service
public class AgricultorImpl implements IAgricultor {

    @Autowired
    private AgricultorRepository agricultor;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Override
    public List<AgricultorEntity> listaAgricultores() {
        return agricultor.findAll();
    }

    @Override
    public ResponseEntity<AgricultorEntity> crearAgricultor(AgricultorDto dto) {

        try {
            final AgricultorEntity agri = agricultor.save(
                    AgricultorEntity.builder()
                            .nitAgricultor(dto.getNitAgricultor())
                            .nombre(dto.getNombre())
                            .activo(dto.getActivo())
                            .observaciones(dto.getObservaciones())
                            .fecha(new Date())
                            .build()
            );

            bitacora.save(
                    HistoricoBitacoraEntity.builder()
                            .idRegistro(dto.getNitAgricultor())
                            .accion("INSERT")
                            .tabla("agricultor")
                            .activo(true)
                            .usuarioAgrego("localHost")
                            .fechaAccion(new Date())
                            .data(new Gson().toJson(agri))
                            .build()
            );
        } catch (Exception e) {
            return new ResponseEntity("Ocurrió un error: " + e,
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Agricultor creado con exito.",
                HttpStatus.CREATED);
    }

}
