/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.dtos.CuentaDto;
import com.cafetito.dtos.RechazoDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.EstadosEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.peso.PesoCuentaEntity;
import com.cafetito.entity.peso.PesoEstadoEntity;
import com.cafetito.repository.CuentaRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.peso.PesoCuentaBeneficioRepository;
import com.cafetito.service.ICuenta;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class CuentaImpl implements ICuenta {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Autowired
    private PesoCuentaBeneficioRepository cuentaPesoCabal;

    Integer id;

    /**
     * @param nitAgricultor
     * @return data Servicio para obtener todas las cuentas de un agricultor.
     */
    @Override
    public List<CuentaEntity> listarCuentaAgricultor(String nitAgricultor) {
        return cuentaRepository.listarCuentas(nitAgricultor);
    }

    /**
     * @return Servicio para obtener la respuesta del WS.
     */
    @Override
    public String wsStatus() {
        return "Activo";
    }

//    public Integer getId() {
//        return cuentaRepository.requestNexVal();
//    }
    @Override
    public ResponseEntity<CuentaEntity> stateCloseAccount(int idCuenta) {
        final CuentaEntity updateState = cuentaRepository.findById(idCuenta).orElse(null);
        final PesoCuentaEntity pesoAccount = cuentaPesoCabal.findById(idCuenta).orElse(null);
        if (updateState != null) {
            if (updateState.getIdEstado().getIdEstado() == 4) {
                updateState.setIdEstado(new EstadosEntity(5));
                cuentaRepository.save(updateState);

                //Metodo para guardar en bitacora del Beneficio
                bitacora.save(
                        HistoricoBitacoraEntity.builder()
                                .idRegistro(String.valueOf(idCuenta))
                                .accion("UPDATE")
                                .tabla("cuenta")
                                .estadoAnterior(4)
                                .estadoNuevo(5)
                                .usuarioAgrego("localhost")
                                .fechaAccion(new Date())
                                .build()
                );

                //Metodo para actualizar al estado Cuenta Cerrada de la cuenta en PESO CABAL
                pesoAccount.setIdEstado(new PesoEstadoEntity(5));
                pesoAccount.setUsuarioModifica("localhost");
                pesoAccount.setFechaModifico(new Date());
                cuentaPesoCabal.save(pesoAccount);

            } else {
                return new ResponseEntity("La cuenta se encuentra en estado: "
                        + updateState.getIdEstado().getNombre() + " nos es posible Cerrar la cueta",
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("No se encontro la cuenta ingresada",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Cuenta Cerrada correctamente",
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CuentaEntity> stateConfirmedAccount(int idCuenta) {
        final CuentaEntity updateAccount = cuentaRepository.findById(idCuenta).orElse(null);
        Double max = 0.0;
        Double min = 0.0;

        if (updateAccount != null) {
            if (updateAccount.getIdEstado().getIdEstado() == 5) {

                max = updateAccount.getPesoEnviado() * 1.05;
                min = updateAccount.getPesoEnviado() * 0.95;

                //Validación si el peso obtenido esta dentro de los parametros
                if (updateAccount.getPesoTotalObtenido() >= min && updateAccount.getPesoTotalObtenido() <= max) {
                    updateAccount.setTolerancia(true);
                    updateAccount.setIdEstado(new EstadosEntity(6));
                    updateAccount.setResultadoTolerancia("Aceptado, en parametro");
                    cuentaRepository.save(updateAccount);

                    //Metodo para guardar en bitacora del Beneficio
                    bitacora.save(
                            HistoricoBitacoraEntity.builder()
                                    .idRegistro(String.valueOf(idCuenta))
                                    .accion("UPDATE")
                                    .tabla("cuenta")
                                    .estadoAnterior(5)
                                    .estadoNuevo(6)
                                    .activo(true)
                                    .usuarioAgrego("localhost")
                                    .fechaAccion(new Date())
                                    .build()
                    );
                } else {
                    //validación si el peso obtenido es menor al -5%
                    if (updateAccount.getPesoTotalObtenido() < min) {
                        updateAccount.setIdEstado(new EstadosEntity(6));
                        updateAccount.setTolerancia(false);
                        updateAccount.setResultadoTolerancia("Faltante");
                        cuentaRepository.save(updateAccount);

                        //Metodo para guardar en bitacora del Beneficio
                        bitacora.save(
                                HistoricoBitacoraEntity.builder()
                                        .idRegistro(String.valueOf(idCuenta))
                                        .accion("UPDATE")
                                        .tabla("cuenta")
                                        .estadoAnterior(5)
                                        .estadoNuevo(6)
                                        .activo(false)
                                        .usuarioAgrego("localhost")
                                        .fechaAccion(new Date())
                                        .build()
                        );
                    }
                    //validación si el peso obtenido es mayor al -5%
                    if (updateAccount.getPesoTotalObtenido() > max) {
                        updateAccount.setIdEstado(new EstadosEntity(6));
                        updateAccount.setTolerancia(false);
                        updateAccount.setResultadoTolerancia("Sobrante");
                        cuentaRepository.save(updateAccount);

                        //Metodo para guardar en bitacora del Beneficio
                        bitacora.save(
                                HistoricoBitacoraEntity.builder()
                                        .idRegistro(String.valueOf(idCuenta))
                                        .accion("UPDATE")
                                        .tabla("cuenta")
                                        .estadoAnterior(5)
                                        .estadoNuevo(6)
                                        .activo(false)
                                        .usuarioAgrego("localhost")
                                        .fechaAccion(new Date())
                                        .build()
                        );
                    }
                }
            } else {
                return new ResponseEntity("La cuenta se encuentra en estado: "
                        + updateAccount.getIdEstado().getNombre() + " nos es posible Cerrar la cueta",
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("No se encontro la cuenta ingresada",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Cuenta confirmada correctamente",
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CuentaEntity> declineAccount(RechazoDto dto) {
        final CuentaEntity updateAccount = cuentaRepository.findById(dto.getIdCuenta()).orElse(null);

        if (updateAccount != null) {
            if (updateAccount.getIdEstado().getIdEstado() == 1) {

                updateAccount.setComentario(dto.getComentario());
                updateAccount.setIdEstado(new EstadosEntity(7));
                cuentaRepository.save(updateAccount);

                //Metodo para guardar en bitacora del Beneficio
                bitacora.save(
                        HistoricoBitacoraEntity.builder()
                                .idRegistro(String.valueOf(dto.getIdCuenta()))
                                .accion("UPDATE")
                                .tabla("cuenta")
                                .estadoAnterior(1)
                                .estadoNuevo(7)
                                .activo(false)
                                .usuarioAgrego(dto.getUsuarioAgrego())
                                .fechaAccion(new Date())
                                .build()
                );

            } else {
                return new ResponseEntity("La cuenta se encuentra en estado: "
                        + updateAccount.getIdEstado().getNombre() + " nos es posible Cerrar la cueta",
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("No se encontro la cuenta ingresada",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Cuenta rechazada correctamente",
                HttpStatus.OK);
    }

//
//    @Override
//    public String createCuenta(CuentaDto dto) {
//        this.id = getId();
//        cuentaRepository.save(
//                CuentaEntity.builder()
//                        .idCuenta(this.id)
//                        .nitAgricultor(new AgricultorEntity(dto.getNitAgricultor()))
//                        .idPesaje(dto.getIdPesaje())
//                        .idEstado(new EstadosEntity(1))
//                        .fechaCreacion(new Date())
//                        .pesoEnviado(dto.getPesoEnviado())
//                        .build()
//        );
//        
//         bitacora.save(
//                HistoricoBitacoraEntity.builder()
//                        .idRegistro(String.valueOf(this.id))
//                        .accion("INSERT")
//                        .tabla("cuenta")
//                        .estadoAnterior(0)
//                        .estadoNuevo(1)
//                        .usuarioAgrego("localHost")
//                        .fechaAccion(new Date())
//                        .build()
//        );
//
//        return null;
//    }

    @Override
    public List<CuentaEntity> listarCuentaAgricultorPorEstado(String nitAgricultor, Integer state) {
       return cuentaRepository.listarCuentasEstado(nitAgricultor, state);
    }
}
