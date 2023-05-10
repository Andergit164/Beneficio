/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.controller;

import com.cafetito.dtos.peso.PesoCuentaBeneficioDto;
import com.cafetito.entity.peso.PesoCuentaEntity;
import com.cafetito.entity.peso.PesoParcialidadEntity;
import com.cafetito.service.peso.ICuentaBeneficio;
import com.cafetito.service.peso.IPesoParcialidad;
import java.util.List;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anderson
 */
@Produces({"application/json"})
@RestController
@RequestMapping("/peso")
@CrossOrigin(origins = ("*"))
public class PesoController {
    
    @Autowired
    private IPesoParcialidad peso;
    
    @Autowired
    private ICuentaBeneficio cuenta;
    
    /**
     * @param idCuenta
     * @return 
     * @url rutas para la entidad parcialidad
     */
    @RequestMapping(value = "/count/parts/{idCuenta}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PesoParcialidadEntity> listParts(
            @PathVariable("idCuenta") int idCuenta) {
        return peso.listarParcialidades(idCuenta);
    }
    
    @RequestMapping(value = "/update/weigth/{idParcialidad}/{wigth}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public PesoParcialidadEntity updateState(
            @PathVariable("idParcialidad") int idParcialidad,
            @PathVariable("wigth") double wigth) {
        return peso.actualiarPeso(idParcialidad, wigth);
    }
    
    /**
     * @param idCuenta
     * @return 
     * @url rutas para la entidad cuentas
     */
    
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PesoCuentaEntity> listCounts() {
        return cuenta.listarCuentas();
    }
    
    @RequestMapping(value = "/create/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCount(
            @RequestBody PesoCuentaBeneficioDto dto) {
        return cuenta.createCuenta(dto);
    }
}
