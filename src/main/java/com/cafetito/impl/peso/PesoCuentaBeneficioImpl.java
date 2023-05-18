/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.peso;

import com.cafetito.dtos.peso.PesoCuentaBeneficioDto;
import com.cafetito.entity.peso.PesoCuentaEntity;
import com.cafetito.entity.peso.PesoEstadoEntity;
import com.cafetito.repository.peso.PesoCuentaBeneficioRepository;
import com.cafetito.service.peso.ICuentaBeneficio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class PesoCuentaBeneficioImpl implements ICuentaBeneficio {

    @Autowired
    private PesoCuentaBeneficioRepository cuentaBeneficio;

//    @Override
//    public String createCuenta(PesoCuentaBeneficioDto dto) {
//        cuentaBeneficio.save(PesoCuentaEntity.builder()
//                .idCuenta(dto.getIdCuenta())
//                .idPesaje(dto.getIdPesaje())
//                .idEstado(new PesoEstadoEntity(dto.getEstadoCuenta()))
//                .usuarioAgrega("localhost")
//                .fechaCreacion(new Date())
//                .build()
//        );
//        return null;
//    }

    @Override
    public List<PesoCuentaEntity> listarCuentas() {
        return cuentaBeneficio.listCuentas();
    }

}
