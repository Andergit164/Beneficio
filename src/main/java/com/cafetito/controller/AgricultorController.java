/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.controller;

import com.cafetito.dtos.agricultor.TransporteAgriDto;
import com.cafetito.dtos.agricultor.TransportistaAgriDto;
import com.cafetito.entity.agricultor.TransporteAgriEntity;
import com.cafetito.entity.agricultor.TransportistaAgriEntity;
import com.cafetito.service.agricultor.ITransporteAgri;
import com.cafetito.service.agricultor.ITransportistaAgri;
import java.util.List;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/Agricultor")
public class AgricultorController {
    
    @Autowired
    private ITransporteAgri transporte;
    
    @Autowired
    private ITransportistaAgri transportista;
    
    /**
     * @param dto
     * @return 
     * @url rutas para la entidad transporte
     */
    
    @RequestMapping(value = "/create/transport", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createTransport(
            @RequestBody TransporteAgriDto dto) {
        return transporte.agregarTransporte(dto);
    }
    
    @RequestMapping(value = "/list/transport", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TransporteAgriEntity> listTransport(){
        return transporte.listarTransporte();
    }
    
     /**
     * @param dto
     * @return 
     * @url rutas para la entidad transportista
     */
    
    @RequestMapping(value = "/create/carrier", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCarrier(
            @RequestBody TransportistaAgriDto dto) {
        return transportista.crearTransportista(dto);
    }
    
    @RequestMapping(value = "/list/carrier", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TransportistaAgriEntity> listCarrier(){
        return transportista.listarTransportistas();
    }
    
}
