/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.TransportistaAgriDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.entity.peso.TransporteAgriEntity;
import com.cafetito.entity.peso.TransportistaAgriEntity;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.TransportistaRepository;
import com.cafetito.repository.peso.TransportistaAgriRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafetito.service.agricultor.ITransportistaAgri;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
@Service
public class TransportistaAgriImpl implements ITransportistaAgri {

    @Autowired
    private TransportistaAgriRepository transportista;

    @Autowired
    private TransportistaRepository transportistaRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Override
    public ResponseEntity<TransportistaAgriEntity> crearTransportista(TransportistaAgriDto dto) {

        if (dto.getFechaLicencia().after(new Date())) {
            try {

                //Metodo para crear un trasportista en el Agricultor.
                transportista.save(
                        TransportistaAgriEntity.builder()
                                .idTransportista(dto.getIdTransportista())
                                .nombre(dto.getNombre())
                                .fechaNacimiento(dto.getFechaNacimiento())
                                .activo(true)
                                .disponible(true)
                                .tipoLicencia(dto.getTipoLicencia())
                                .fechaLicencia(dto.getFechaLicencia())
                                .usuarioAgrega(dto.getUsuarioAgrego())
                                .fechaCreacion(new Date())
                                .build()
                );

                //Metodo para crear un transportista en el Beneficio.
                final TransportistaEntity carrier = transportistaRepository.save(
                        TransportistaEntity.builder()
                                .idTransportista(dto.getIdTransportista())
                                .nitAgricultor(new AgricultorEntity(dto.getNitAgricultor()))
                                .nombre(dto.getNombre())
                                .estado("Activo")
                                .fechaCreacion(new Date())
                                .build()
                );

                //Metodo para guradar en bitacora del Beneficio.
                bitacora.save(
                        HistoricoBitacoraEntity.builder()
                                .idRegistro(String.valueOf(dto.getIdTransportista()))
                                .accion("INSERT")
                                .tabla("transportista")
                                .activo(false)
                                .usuarioAgrego(dto.getUsuarioAgrego())
                                .fechaAccion(new Date())
                                .data(new Gson().toJson(carrier))
                                .build()
                );
            } catch (Exception e) {
                return new ResponseEntity("Error: " + e, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("La licencia se encuentra vencida", HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity("Transportista Creado", HttpStatus.CREATED);
    }

    @Override
    public List<TransportistaAgriEntity> listarTransportistas() {
        return transportista.findAll();
    }

    @Override
    public List<TransportistaAgriEntity> carrierForAssignment(Integer idPesaje) {
       return transportista.carrierForAssign(idPesaje);
    }

}
