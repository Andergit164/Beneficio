/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.dtos.agricultor.ParcialidadAgriDto;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.HistoricoBitacoraEntity;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.entity.peso.ParcialidadAgriEntity;
import com.cafetito.entity.peso.PesajeAgriEntity;
import com.cafetito.entity.peso.TransporteAgriEntity;
import com.cafetito.entity.peso.TransportistaAgriEntity;
import com.cafetito.repository.CuentaRepository;
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.ParcialidadRepository;
import com.cafetito.repository.peso.ParcialidadAgriRepository;
import com.cafetito.repository.peso.PesajeAgriRepository;
import com.cafetito.service.agricultor.IParcialidadAgri;
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

    Integer idParcialidadAgricultor;
    Integer idParcialidadBeneficio;
    Integer totalParcialidades;
    Double pesoTotal = 0.0;

    @Override
    public ResponseEntity<ParcialidadAgriEntity> crearParcialidad(ParcialidadAgriDto dto) {

        final CuentaEntity beneficioCount = beneficioCuenta.findById(dto.getIdCuenta()).orElse(null);
        final PesajeAgriEntity pesajeAgri = pesaje.findById(dto.getIdPesaje()).orElse(null);
        final CuentaEntity countBene = beneficioCuenta.findById(dto.getIdCuenta()).orElse(null);

        if (beneficioCount.getIdEstado().getIdEstado() == 1) {

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

            //Metodo utilizado para crear una parcialidad en el Beneficio
            parcialidadBeneficio.save(
                    ParcialidadEntity.builder()
                            .idParcialidad(this.idParcialidadBeneficio)
                            .idCuenta(new CuentaEntity(dto.getIdCuenta()))
                            .idTransporte(new TransporteEntity(dto.getIdTransporte()))
                            .idTransportista(new TransportistaEntity(dto.getIdTransportista()))
                            .idParcialidadAgricultor(this.idParcialidadAgricultor)
                            .pesoEnviado(dto.getPesoParcialidad())
                            .valido(false)
                            .tipoMedida(dto.getTipoMedida())
                            .recibido("Espera de ingreso")
                            .build()
            );

            countBene.setTotalParcialidades(this.totalParcialidades);
            countBene.setPesoEnviado(this.pesoTotal);
            beneficioCuenta.save(countBene);

            //Metodo utilizado para guardar en bitacora la creacion de la parcialidad en el beneficio
            bitacora.save(
                    HistoricoBitacoraEntity.builder()
                            .idRegistro(String.valueOf(this.idParcialidadBeneficio))
                            .accion("INSERT")
                            .tabla("parcialidad")
                            .activo(false)
                            .usuarioAgrego(dto.getUsuarioAgrego())
                            .fechaAccion(new Date())
                            .build()
            );
        } else {
            return new ResponseEntity("La cuenta se encuentra en estado: " + beneficioCount.getIdEstado().getNombre() + " nos es posible agregar mas parcialidades",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Parcialidad Ingresada",
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
