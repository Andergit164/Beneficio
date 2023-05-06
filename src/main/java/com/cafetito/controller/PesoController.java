/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.controller;

import com.cafetito.entity.peso.PesoParcialidadEntity;
import com.cafetito.service.peso.IPesoParcialidad;
import java.util.List;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
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
public class PesoController {
    
    @Autowired
    private IPesoParcialidad peso;
    
    /**
     * @param idCuenta
     * @return 
     * @url rutas para la entidad parcialidad
     */
    @RequestMapping(value = "/count/parts/{idCuenta}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_PESOCABAL')")
    public List<PesoParcialidadEntity> listParts(
            @PathVariable("idCuenta") int idCuenta) {
        return peso.listarParcialidades(idCuenta);
    }
}
