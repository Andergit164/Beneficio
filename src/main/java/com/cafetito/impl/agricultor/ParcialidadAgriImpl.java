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
import com.cafetito.repository.HistoricoBitacoraRepository;
import com.cafetito.repository.ParcialidadRepository;
import com.cafetito.repository.peso.ParcialidadAgriRepository;
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

    Integer idParcialidadAgricultor;
    Integer idParcialidadBeneficio;

    @Override
    public Boolean crearParcialidad(ParcialidadAgriDto dto) {
        
        this.idParcialidadAgricultor = getIdAgricultor();
        this.idParcialidadBeneficio = getIdBeneficio();

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
                        .usuarioAgrega("localhost")
                        .build()
        );
        
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
                        .recibido("Espera de ingreso")
                        .build()
        );

         //Metodo utilizado para guardar en bitacora la creacion de la parcialidad en el beneficio
        bitacora.save(
                HistoricoBitacoraEntity.builder()
                        .idRegistro(String.valueOf(this.idParcialidadBeneficio))
                        .accion("INSERT")
                        .tabla("parcialidad")
                        .activo(false)
                        .usuarioAgrego("localHost")
                        .fechaAccion(new Date())
                        .build() 
        );

        return true;
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

    @Override
    public List<ParcialidadAgriEntity> listarParcialidades(Integer idPesaje) {
        return parcialidad.listarParcialidades(idPesaje);
    }

}
