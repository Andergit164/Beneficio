/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.google.gson.Gson;
import com.cafetito.dtos.agricultor.TransporteAgriDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.entity.peso.TransporteAgriEntity;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.TransporteRepository;
import com.cafetito.repository.peso.TransporteAgriRepository;
import com.cafetito.service.agricultor.ITransporteAgri;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anderson
 */
@Service
public class TransporteAgriImpl implements ITransporteAgri {

    @Autowired
    private TransporteAgriRepository transporte;

    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Override
    public ResponseEntity<TransporteAgriEntity> agregarTransporte(TransporteAgriDto dto) {

        try {
            //Metodo para crear un transporte en el Agricultor
            transporte.save(
                    TransporteAgriEntity.builder()
                            .placa(dto.getPlaca())
                            .marca(dto.getMarca())
                            .color(dto.getColor())
                            .modelo(dto.getModelo())
                            .linea(dto.getLinea())
                            .activo(true)
                            .disponible(true)
                            .usuarioAgrega(dto.getUsuarioAgrego())
                            .fechaCreacion(new Date())
                            .build()
            );

            //Metodo para crear un transporte en el Beneficio
            final TransporteEntity transporte = transporteRepository.save(
                    TransporteEntity.builder()
                            .idTransporte(dto.getPlaca())
                            .nitAgricultor(new AgricultorEntity(dto.getNitAgricultor()))
                            .fechaCreacion(new Date())
                            .estado("Activo")
                            .build()
            );

            //Metodo para guardar en bitacora del Beneficio
            bitacora.save(
                    HistoricoBitacoraEntity.builder()
                            .idRegistro(dto.getPlaca())
                            .accion("INSERT")
                            .tabla("transporte")
                            .activo(false)
                            .usuarioAgrego(dto.getUsuarioAgrego())
                            .fechaAccion(new Date())
                            .data(new Gson().toJson(transporte))
                            .build()
            );
        } catch (Exception e) {
            return new ResponseEntity("Error: " + e, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Transporte Creado", HttpStatus.CREATED);
    }

    @Override
    public List<TransporteAgriEntity> listarTransporte() {
        return transporte.findAll();
    }

    @Override
    public List<TransporteAgriEntity> transportForAssignment(Integer idPesaje) {
        return transporte.transportForAssign(idPesaje);
    }

}
