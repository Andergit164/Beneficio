/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.service.peso;

import com.cafetito.dtos.peso.PesoCuentaBeneficioDto;
import com.cafetito.entity.peso.CuentaBeneficioEntity;
import java.util.List;

/**
 *
 * @author Anderson
 */
public interface ICuentaBeneficio {

    String createCuenta(PesoCuentaBeneficioDto dto);

    List<CuentaBeneficioEntity> listarCuentas();

}
