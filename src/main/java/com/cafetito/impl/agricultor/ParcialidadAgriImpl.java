/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.ParcialidadAgriDto;
import com.cafetito.entity.CatalogoEntity;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.entity.peso.CatalogoAgriEntity;
import com.cafetito.entity.peso.ParcialidadAgriEntity;
import com.cafetito.entity.peso.PesajeAgriEntity;
import com.cafetito.entity.peso.TransporteAgriEntity;
import com.cafetito.entity.peso.TransportistaAgriEntity;
import com.cafetito.repository.CuentaRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.ParcialidadRepository;
import com.cafetito.repository.peso.ParcialidadAgriRepository;
import com.cafetito.repository.peso.PesajeAgriRepository;
import com.cafetito.repository.peso.TransporteAgriRepository;
import com.cafetito.repository.peso.TransportistaAgriRepository;
import com.cafetito.service.agricultor.IParcialidadAgri;
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
public class ParcialidadAgriImpl implements IParcialidadAgri {

    @Autowired
    private ParcialidadAgriRepository parcialidad;

    @Autowired
    private ParcialidadRepository parcialidadBeneficio;

    @Autowired
    private HistoricoBitacoraRepository bitacora;

    @Autowired
    private CuentaRepository beneficioCuenta;

    @Autowired
    private PesajeAgriRepository pesaje;

    @Autowired
    private TransporteAgriRepository Atransporte;

    @Autowired
    private TransportistaAgriRepository Atransportista;

    Integer idParcialidadAgricultor;
    Integer idParcialidadBeneficio;
    Integer totalParcialidades;
    Double pesoTotal = 0.0;

    @Override
    public ResponseEntity<ParcialidadAgriEntity> crearParcialidad(ParcialidadAgriDto dto) {

        final CuentaEntity beneficioCount = beneficioCuenta.findById(dto.getIdCuenta()).orElse(null);
        final PesajeAgriEntity pesajeAgri = pesaje.findById(dto.getIdPesaje()).orElse(null);
        final TransporteAgriEntity AgriTransporte = Atransporte.findById(dto.getIdTransporte()).orElse(null);
        final TransportistaAgriEntity AgriTransportista = Atransportista.findById(dto.getIdTransportista()).orElse(null);

        if (beneficioCount.getIdEstado().getIdEstado() == 1) {
            if (AgriTransporte.getDisponible() || AgriTransporte.getIdPesaje() == dto.getIdPesaje()) {
                if (AgriTransportista.getDisponible() || AgriTransportista.getIdPesaje() == dto.getIdPesaje()) {

                    this.idParcialidadAgricultor = getIdAgricultor();
                    this.idParcialidadBeneficio = getIdBeneficio();
                    this.totalParcialidades = getCountPart(dto.getIdPesaje());

                    //Metodo utilizado para crear una parcialidad en el Agricultor
                    parcialidad.save(
                            ParcialidadAgriEntity.builder()
                                    .idParcialidad(this.idParcialidadAgricultor)
                                    .idPesaje(new PesajeAgriEntity(dto.getIdPesaje()))
                                    .idTransporte(new TransporteAgriEntity(dto.getIdTransporte()))
                                    .idTransportista(new TransportistaAgriEntity(dto.getIdTransportista()))
                                    .idParcialidadBeneficio(this.idParcialidadBeneficio)
                                    .pesoParcialidad(dto.getPesoParcialidad())
                                    .tipoMedida(dto.getTipoMedida())
                                    .medidaPeso(new CatalogoAgriEntity(pesajeAgri.getMedidaPeso().getCodigo()))
                                    .fechaCreacion(new Date())
                                    .usuarioAgrega(dto.getUsuarioAgrego())
                                    .build()
                    );

                    this.listarParcialidades(dto.getIdPesaje()).forEach(peso -> {
                        this.pesoTotal += peso.getPesoParcialidad();
                    });

                    pesajeAgri.setTotalParcialidades(this.totalParcialidades);
                    pesajeAgri.setPesoTotal(this.pesoTotal);
                    pesajeAgri.setUsuarioModifica(dto.getUsuarioAgrego());
                    pesajeAgri.setFechaModifico(new Date());
                    pesaje.save(pesajeAgri);

                    AgriTransporte.setDisponible(false);
                    AgriTransporte.setIdPesaje(dto.getIdPesaje());
                    AgriTransporte.setUsuarioModifica(dto.getUsuarioAgrego());
                    AgriTransporte.setFechaModifico(new Date());
                    Atransporte.save(AgriTransporte);

                    AgriTransportista.setDisponible(false);
                    AgriTransportista.setIdPesaje(dto.getIdPesaje());
                    AgriTransportista.setUsuarioModifica(dto.getUsuarioAgrego());
                    AgriTransportista.setFechaModifico(new Date());
                    Atransportista.save(AgriTransportista);

                    //Metodo utilizado para crear una parcialidad en el Beneficio
                    final ParcialidadEntity part = parcialidadBeneficio.save(
                            ParcialidadEntity.builder()
                                    .idParcialidad(this.idParcialidadBeneficio)
                                    .idCuenta(new CuentaEntity(dto.getIdCuenta()))
                                    .idTransporte(new TransporteEntity(dto.getIdTransporte()))
                                    .idTransportista(new TransportistaEntity(dto.getIdTransportista()))
                                    .idParcialidadAgricultor(this.idParcialidadAgricultor)
                                    .pesoEnviado(dto.getPesoParcialidad())
                                    .valido(false)
                                    .tipoMedida(dto.getTipoMedida())
                                    .medidaPeso(new CatalogoEntity(pesajeAgri.getMedidaPeso().getCodigo()))
                                    .recibido("Espera de ingreso")
                                    .build()
                    );

                    beneficioCount.setTotalParcialidades(this.totalParcialidades);
                    beneficioCount.setPesoEnviado(this.pesoTotal);
                    beneficioCuenta.save(beneficioCount);
                    this.pesoTotal = 0.0;

                    //Metodo utilizado para guardar en bitacora la modificacion de la cuenta en el BENEFICIO
                    bitacora.save(
                            HistoricoBitacoraEntity.builder()
                                    .idRegistro(String.valueOf(this.idParcialidadBeneficio))
                                    .accion("UPDATE")
                                    .tabla("cuenta")
                                    .estadoAnterior(beneficioCount.getIdEstado().getIdEstado())
                                    .estadoNuevo(beneficioCount.getIdEstado().getIdEstado())
                                    .usuarioAgrego(dto.getUsuarioAgrego())
                                    .fechaAccion(new Date())
                                    .data(new Gson().toJson(beneficioCount))
                                    .build()
                    );

                    //Metodo utilizado para guardar en bitacora la creacion de la parcialidad en el beneficio
                    bitacora.save(
                            HistoricoBitacoraEntity.builder()
                                    .idRegistro(String.valueOf(this.idParcialidadBeneficio))
                                    .accion("INSERT")
                                    .tabla("parcialidad")
                                    .activo(false)
                                    .usuarioAgrego(dto.getUsuarioAgrego())
                                    .fechaAccion(new Date())
                                    .data(new Gson().toJson(part))
                                    .build()
                    );

                } else {
                    return new ResponseEntity("El transportista: [" + AgriTransportista.getIdTransportista() + "] no se encuentra disponible",
                            HttpStatus.BAD_REQUEST);
                }

            } else {
                return new ResponseEntity("El transporte: [" + AgriTransporte.getPlaca() + "] no se encuentra disponible",
                        HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity("La cuenta se encuentra en estado: '" + beneficioCount.getIdEstado().getNombre() + "' no es posible agregar más parcialidades",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Parcialidad ingresada correctamente",
                HttpStatus.CREATED);
    }

    //Metodo utilizado para obtener el id de la parcialidad para el Beneficio
    public Integer getIdBeneficio() {
        Integer id;
        id = parcialidadBeneficio.requestNexVal();
        return id;
    }

    //Metodo utilizado para obtener el id de la parcialidad para el Agricultor
    public Integer getIdAgricultor() {
        Integer id;
        id = parcialidad.requestNexValAgricultor();
        return id;
    }

    //Metodo utilizado para obtener el total de parcialidades
    public Integer getCountPart(Integer idPesaje) {
        Integer id;
        id = parcialidad.requestNexValPart(idPesaje);
        return id;
    }

    @Override
    public List<ParcialidadAgriEntity> listarParcialidades(Integer idPesaje) {
        return parcialidad.listarParcialidades(idPesaje);
    }

}
