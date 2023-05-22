/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.RechazoDto;
import com.cafetito.dtos.updateParcialidadDto;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.EstadosEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.peso.PesoCuentaEntity;
import com.cafetito.entity.peso.PesoEstadoEntity;
import com.cafetito.entity.peso.PesoParcialidadEntity;
import com.cafetito.entity.peso.TransporteAgriEntity;
import com.cafetito.entity.peso.TransportistaAgriEntity;
import com.cafetito.repository.CuentaRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.ParcialidadRepository;
import com.cafetito.repository.peso.PesoCuentaBeneficioRepository;
import com.cafetito.repository.peso.PesoParcialidadRepository;
import com.cafetito.repository.peso.TransporteAgriRepository;
import com.cafetito.repository.peso.TransportistaAgriRepository;
import com.cafetito.service.IParcialidad;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Anderson
 */
@Service
@Slf4j
public class ParcialidadImpl implements IParcialidad {

    @Autowired
    private ParcialidadRepository parcialidadRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private PesoCuentaBeneficioRepository cuentaBeneficio;

    @Autowired
    private PesoParcialidadRepository pesoParcialidadRepository;

    @Autowired
    private TransporteAgriRepository Atransporte;

    @Autowired
    private TransportistaAgriRepository Atransportista;

    @Override
    public List<ParcialidadEntity> listarParcialidades(Integer idCuenta) {
        return parcialidadRepository.listParcialidad(idCuenta);
    }

    @Override
    public ResponseEntity<ParcialidadEntity> ingresoParcialidad(updateParcialidadDto dto) {
        final CuentaEntity count = cuentaRepository.findById(dto.getIdCuenta()).orElse(null);
        final ParcialidadEntity part = parcialidadRepository.findById(dto.getIdParcialidad()).orElse(null);
        final TransporteAgriEntity AgriTransporte = Atransporte.findById(part.getIdTransporte().getIdTransporte()).orElse(null);
        final TransportistaAgriEntity AgriTransportista = Atransportista.findById(part.getIdTransportista().getIdTransportista()).orElse(null);

        if (part != null) {
            if (count != null) {
                if (part.isValido() == false) {
                    if (part.getIdTransporte().isActivo() == true) {
                        if (part.getIdTransportista().isActivo() == true) {

                            //Metodo para actualizar la parcialidad a recibida
                            part.setValido(true);
                            part.setRecibido("Ingresado");
                            part.setFechaRecepcionParcialidad(new Date());
                            parcialidadRepository.save(part);

                            AgriTransporte.setDisponible(true);
                            AgriTransporte.setUsuarioModifica(dto.getUsuarioModifico());
                            AgriTransporte.setFechaModifico(new Date());
                            Atransporte.save(AgriTransporte);

                            AgriTransportista.setDisponible(true);
                            AgriTransportista.setUsuarioModifica(dto.getUsuarioModifico());
                            AgriTransportista.setFechaModifico(new Date());
                            Atransportista.save(AgriTransportista);

                            //Metodo para guardar en bitacora del Beneficio
                            bitacora.save(
                                    HistoricoBitacoraEntity.builder()
                                            .idRegistro(String.valueOf(dto.getIdParcialidad()))
                                            .accion("UPDATE")
                                            .tabla("parcialidad")
                                            .activo(true)
                                            .usuarioAgrego(dto.getUsuarioModifico())
                                            .fechaAccion(new Date())
                                            .data(new Gson().toJson(part))
                                            .build()
                            );

                            //Metodo para crear la cuenta en PESO CABAL
                            cuentaBeneficio.save(PesoCuentaEntity.builder()
                                    .idCuenta(count.getIdCuenta())
                                    .idPesaje(count.getIdPesaje())
                                    .idEstado(new PesoEstadoEntity(2))
                                    .usuarioAgrega(dto.getUsuarioModifico())
                                    .fechaCreacion(new Date())
                                    .build()
                            );

                            //Metodo para crear la parcialidad en PESO CABAL
                            pesoParcialidadRepository.save(
                                    PesoParcialidadEntity.builder()
                                            .idParcialidad(dto.getIdParcialidad())
                                            .idCuenta(new PesoCuentaEntity(dto.getIdCuenta()))
                                            .idTransporte(part.getIdTransporte().getIdTransporte())
                                            .idTransportista(part.getIdTransportista().getIdTransportista())
                                            .tipoMedida(part.getTipoMedida())
                                            .aceptado(true)
                                            .usuarioAgrega(dto.getUsuarioModifico())
                                            .fechaCreacion(new Date())
                                            .build()
                            );

                            if (count.getIdEstado().getIdEstado() == 1) {

                                //Metodo para actualizar la cuenta al estado "Cuenta Abierta (2)"
                                count.setIdEstado(new EstadosEntity(2));
                                cuentaRepository.save(count);

                                //Metodo para guardar en bitacora del Beneficio
                                bitacora.save(
                                        HistoricoBitacoraEntity.builder()
                                                .idRegistro(String.valueOf(dto.getIdParcialidad()))
                                                .accion("UPDATE")
                                                .tabla("cuenta")
                                                .estadoAnterior(1)
                                                .estadoNuevo(2)
                                                .usuarioAgrego(dto.getUsuarioModifico())
                                                .fechaAccion(new Date())
                                                .data(new Gson().toJson(count))
                                                .build()
                                );
                            }
                        } else {
                            return new ResponseEntity("El transportista: '" + part.getIdTransportista().getIdTransportista() + "' no se encuentra activo.",
                                    HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity("El transporte: '" + part.getIdTransporte().getIdTransporte() + "' no se cuentra activo.",
                                HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity("La parcialidad: '" + dto.getIdParcialidad() + "' ya fue recibida.",
                            HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("No se encontro la cuenta ingresada.",
                        HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity("No se encontro la parcialidad ingresada.",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Parcialidad recibida correctamente.",
                HttpStatus.OK);
    }

    @Override
    public Optional<ParcialidadEntity> showPart(int idParcialidad) {
        return parcialidadRepository.findById(idParcialidad);
    }

    @Override
    public ResponseEntity<ParcialidadEntity> declineAccount(RechazoDto dto) {
        final ParcialidadEntity declinePart = parcialidadRepository.findById(dto.getId()).orElse(null);

        if (declinePart != null) {

            declinePart.setComentario(dto.getComentario());
            declinePart.setValido(false);
            declinePart.setRecibido("Rechazado");
            parcialidadRepository.save(declinePart);

            //Metodo para guardar en bitacora del Beneficio
            bitacora.save(
                    HistoricoBitacoraEntity.builder()
                            .idRegistro(String.valueOf(dto.getId()))
                            .accion("UPDATE")
                            .tabla("parcialidad")
                            .activo(true)
                            .usuarioAgrego(dto.getUsuarioAgrego())
                            .fechaAccion(new Date())
                            .data(new Gson().toJson(declinePart))
                            .build()
            );

        } else {
            return new ResponseEntity("No se encontro la parcialidad ingresada.",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Parcialidad rechazada correctamente",
                HttpStatus.OK);
    }
}

//    Integer id;
//
//    @Override
//    public String createParcialidad(ParcialidadDto dto) {
//        this.id = getId();
//        parcialidadRepository.save(
//                ParcialidadEntity.builder()
//                        .idParcialidad(this.id)
//                        .idCuenta(new CuentaEntity(dto.getIdCuenta()))
//                        .idTransporte(new TransporteEntity(dto.getIdTransporte()))
//                        .idTransportista(new TransportistaEntity(dto.getIdTransportista()))
//                        .pesoEnviado(dto.getPesoEnviado()).recibido("Espera recepción")
//                        .build()
//        );
//
//        bitacora.save(
//                HistoricoBitacoraEntity.builder()
//                        .idRegistro(String.valueOf(this.id))
//                        .accion("INSERT")
//                        .tabla("parcialidad")
//                        .activo(false)
//                        .usuarioAgrego("localHost")
//                        .fechaAccion(new Date())
//                        .build() 
//        );
//        return null;
//    }
//
//    public Integer getId() {
//        Integer id;
//        id = parcialidadRepository.requestNexVal();
//        return id;
//    }

