/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.peso;

import com.cafetito.dtos.peso.PesoParcialidadDto;
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
import com.cafetito.service.peso.IPesoParcialidad;
import com.google.gson.Gson;
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
public class PesoParcialidadImpl implements IPesoParcialidad {

    @Autowired
    private PesoParcialidadRepository pesoParcialidadRepository;

    @Autowired
    private PesoCuentaBeneficioRepository pesoCuenta;

    @Autowired
    private CuentaRepository beneficioCuenta;

    @Autowired
    private ParcialidadRepository beneficioParcialidad;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Autowired
    private TransporteAgriRepository agriTransporte;

    @Autowired
    private TransportistaAgriRepository agriTransportista;

    Double totalPesado = 0.0;
    Double diferencia = 0.0;
    Double diferenciaCuenta = 0.0;

    @Override
    public List<PesoParcialidadEntity> listarParcialidades(Integer idCuenta) {
        return pesoParcialidadRepository.listParcialidad(idCuenta);
    }

    @Override
    public ResponseEntity<PesoParcialidadEntity> actualiarPeso(PesoParcialidadDto dto) {

        Integer parcialidadesPesadas;

        final PesoParcialidadEntity updateWeight = pesoParcialidadRepository.findById(dto.getIdParcialidad()).orElse(null);
        final PesoCuentaEntity count = pesoCuenta.findById(dto.getIdCuenta()).orElse(null);
        final CuentaEntity beneficioCount = beneficioCuenta.findById(dto.getIdCuenta()).orElse(null);
        final ParcialidadEntity beneficioPart = beneficioParcialidad.findById(dto.getIdParcialidad()).orElse(null);
        final TransportistaAgriEntity ATransportista = agriTransportista.findById(beneficioPart.getIdTransportista().getIdTransportista()).orElse(null);
        final TransporteAgriEntity ATransporte = agriTransporte.findById(beneficioPart.getIdTransporte().getIdTransporte()).orElse(null);

        if (updateWeight != null) {
            if (beneficioCount.getIdEstado().getIdEstado() == 2 || beneficioCount.getIdEstado().getIdEstado() == 3 || beneficioCount.getIdEstado().getIdEstado() == 4) {
                if (updateWeight.isAceptado()) {
                    if (updateWeight.getMedidaPeso().getCodigo() == dto.getCodigoPeso()) {

                        //Se ingresa el pesa obtenido por la bascula a PESO CABAL
                        updateWeight.setObservaciones(dto.getObservaciones());
                        updateWeight.setPesoObtenido(dto.getPesoObtenido());
                        updateWeight.setFechaPeso(new Date());
                        updateWeight.setBoleta(true);
                        updateWeight.setFechaBoleta(new Date());
                        updateWeight.setUsuarioModifica(dto.getUsuarioModifico());
                        updateWeight.setFechaModifico(new Date());
                        pesoParcialidadRepository.save(updateWeight);

                        //Se ingresa el pesaje obtenido por peso cabal al BENEFICIO
                        beneficioPart.setPesoBascula(dto.getPesoObtenido());
                        beneficioPart.setFechaPesoBascula(new Date());
                        beneficioPart.setRecibido("Pesaje Realizado");
                        this.diferencia = (dto.getPesoObtenido() - beneficioPart.getPesoEnviado());
                        if (this.diferencia < 0) {
                            this.diferencia = this.diferencia * (-1);
                        }
                        beneficioPart.setDiferenciaPeso(this.diferencia);
                        beneficioParcialidad.save(beneficioPart);

                        //Metodo para guardar en bitacora del Beneficio
                        bitacora.save(
                                HistoricoBitacoraEntity.builder()
                                        .idRegistro(String.valueOf(dto.getIdCuenta()))
                                        .accion("UPDATE")
                                        .tabla("parcialidad")
                                        .activo(true)
                                        .usuarioAgrego(dto.getUsuarioModifico())
                                        .fechaAccion(new Date())
                                        .data(new Gson().toJson(beneficioPart))
                                        .build()
                        );

                        //Metodo para ingresar el total pesado a la cuenta de BENEFICIO
                        this.listParcialidadPesadas(dto.getIdCuenta()).forEach(pesaje -> {
                            this.totalPesado += pesaje.getPesoBascula();
                        });
                        this.diferenciaCuenta = this.totalPesado - beneficioCount.getPesoEnviado();
                        if (this.diferenciaCuenta < 0) {
                            this.diferenciaCuenta = this.diferenciaCuenta * (-1);
                        }
                        beneficioCount.setPesoTotalObtenido(this.totalPesado);
                        beneficioCount.setDiferenciaTotal(this.diferenciaCuenta);
                        beneficioCuenta.save(beneficioCount);
                        this.totalPesado = 0.0;

                        //Metodo para guardar en bitacora del Beneficio
                        bitacora.save(
                                HistoricoBitacoraEntity.builder()
                                        .idRegistro(String.valueOf(dto.getIdCuenta()))
                                        .accion("UPDATE")
                                        .tabla("cuenta")
                                        .usuarioAgrego(dto.getUsuarioModifico())
                                        .fechaAccion(new Date())
                                        .data(new Gson().toJson(beneficioCount))
                                        .build()
                        );

                        //Si es primer pesaje cambia el estado de la cuenta a "Pesaje Iniciado" PESO CABAL
                        if (count.getIdEstado().getIdEstado() == 2) {
                            count.setIdEstado(new PesoEstadoEntity(3));
                            count.setUsuarioModifica(dto.getUsuarioModifico());
                            count.setFechaModifico(new Date());
                            pesoCuenta.save(count);
                        }

                        //Si es primer pesaje cambia el estado de la cuenta a "Pesaje Iniciado" BENEFICIO
                        if (beneficioCount.getIdEstado().getIdEstado() == 2) {
                            beneficioCount.setIdEstado(new EstadosEntity(3));
                            beneficioCuenta.save(beneficioCount);

                            //Metodo para guardar en bitacora del Beneficio
                            bitacora.save(
                                    HistoricoBitacoraEntity.builder()
                                            .idRegistro(String.valueOf(dto.getIdCuenta()))
                                            .accion("UPDATE")
                                            .tabla("cuenta")
                                            .estadoAnterior(2)
                                            .estadoNuevo(3)
                                            .usuarioAgrego(dto.getUsuarioModifico())
                                            .fechaAccion(new Date())
                                            .data(new Gson().toJson(beneficioCount))
                                            .build()
                            );
                        }

                        parcialidadesPesadas = getCountPartsWeighing(dto.getIdCuenta());
                        //Metodo encargado de validar si ya se pesaron todas las parcialidades para asignar el estado "Pesaje Finalizado"
                        if (beneficioCount.getTotalParcialidades() == parcialidadesPesadas) {
                            beneficioCount.setIdEstado(new EstadosEntity(4));
                            beneficioCuenta.save(beneficioCount);

                            //Metodo para guardar en bitacora del Beneficio
                            bitacora.save(
                                    HistoricoBitacoraEntity.builder()
                                            .idRegistro(String.valueOf(dto.getIdCuenta()))
                                            .accion("UPDATE")
                                            .tabla("cuenta")
                                            .estadoAnterior(3)
                                            .estadoNuevo(4)
                                            .usuarioAgrego(dto.getUsuarioModifico())
                                            .fechaAccion(new Date())
                                            .data(new Gson().toJson(beneficioCount))
                                            .build()
                            );
                            
                            count.setIdEstado(new PesoEstadoEntity(4));
                            count.setUsuarioModifica(dto.getUsuarioModifico());
                            count.setFechaModifico(new Date());
                            pesoCuenta.save(count);
                            
                            ATransporte.setDisponible(true);
                            ATransporte.setUsuarioModifica(dto.getUsuarioModifico());
                            ATransporte.setFechaModifico(new Date());
                            agriTransporte.save(ATransporte);
                            
                            ATransportista.setDisponible(true);
                            ATransportista.setUsuarioModifica(dto.getUsuarioModifico());
                            ATransportista.setFechaModifico(new Date());
                            agriTransportista.save(ATransportista);
                        }
                    } else {
                        return new ResponseEntity("El tipo de medida seleccionado "
                                + "no coincide con el tipo de medida de la parcialidad: [ " + updateWeight.getMedidaPeso().getNombre() + " ]",
                                HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity("La parcialidad no se encuentra aceptada",
                            HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity("La cuenta se encuentra en estado: '" + beneficioCount.getIdEstado().getNombre()
                        + "' nos es posible ingresar pesajes",
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("No se encontró la parcialidad ingresada",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Ingreso de pesaje exitoso",
                HttpStatus.OK);
    }

    //Metodo utilizado para obtener las parcialidades ya pesadas
    public Integer getCountPartsWeighing(Integer idCuenta) {
        Integer part;
        part = beneficioParcialidad.requestCountParts(idCuenta);
        return part;
    }

    //metodo para obtener los pesos de las parcialidades ya pesadas
    @Override
    public List<ParcialidadEntity> listParcialidadPesadas(Integer idCuenta) {
        return beneficioParcialidad.listParcialidadPeso(idCuenta);
    }

}
