/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.controller;

import com.cafetito.dtos.CuentaDto;
import com.cafetito.dtos.ParcialidadDto;
import com.cafetito.dtos.TransporteDto;
import com.cafetito.dtos.TransportistaDto;
import com.cafetito.entity.AgricultorEntity;
import com.cafetito.entity.CuentaEntity;
import com.cafetito.entity.ParcialidadEntity;
import com.cafetito.entity.TransporteEntity;
import com.cafetito.entity.TransportistaEntity;
import com.cafetito.service.IAgricultor;
import com.cafetito.service.ICuenta;
import com.cafetito.service.IParcialidad;
import com.cafetito.service.ITransporte;
import com.cafetito.service.ITransportista;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/beneficio")
@CrossOrigin(origins = ("*"))
public class BeneficioController {

    @Autowired
    private ICuenta cuenta;
    
    @Autowired
    private IAgricultor agricultor;
    
    @Autowired
    private ITransporte transporte;
    
    @Autowired
    private ITransportista transportista;
    
    @Autowired
    private IParcialidad parcialidad;

    @GetMapping(path = "/status")
    public String WSstatus() {
        return cuenta.wsStatus();
    }

    /**
     * @param nitAgricultor
     * @return 
     * @url rutas para la entidad cuenta
     */

    @RequestMapping(value = "/count/agricultor/{nitAgricultor}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Crea un nuevo alcance masivo")
    public List<CuentaEntity> listarCuentas(
            @PathVariable("nitAgricultor") String nitAgricultor) {
        return cuenta.listarCuentaAgricultor(nitAgricultor);
    }

    @RequestMapping(value = "/count/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCount(
            @RequestBody CuentaDto cuentaDto) {
        return cuenta.createCuenta(cuentaDto);
    }
    
    /**
     * @return 
     * @url rutas para la entidad agricultor
     */
    
  
    @RequestMapping(value = "/farmer/listar", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AgricultorEntity>> listarAgricultores() {
        List<AgricultorEntity> farmers = agricultor.listaAgricultores();
        return ResponseEntity.ok(farmers);
    }
    
    /**
     * @param dto
     * @return 
     * @url rutas para la entidad transporte
     */
    
    @RequestMapping(value = "/transport/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createTransport(
            @RequestBody TransporteDto dto) {
        return transporte.saveTransport(dto);
    }
    
    @RequestMapping(value = "/transport/list/{nitAgricultor}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TransporteEntity> listTransport(
            @PathVariable("nitAgricultor") String nitAgricultor){
        return transporte.listTransport(nitAgricultor);
    }
    
    /**
     * @param dto
     * @return 
     * @url rutas para la entidad transportista
     */
    

    @RequestMapping(value = "/carrier/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCarrier(
            @RequestBody TransportistaDto dto) {
        return transportista.createCarrier(dto);
    }
    
    @RequestMapping(value = "/carrier/list/{nitAgricultor}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TransportistaEntity> listCarrier(
            @PathVariable("nitAgricultor") String nitAgricultor){
        return transportista.listCarriers(nitAgricultor);
    }
    
     /**
     * @param idCuenta
     * @return 
     * @url rutas para la entidad parcialidad
     */

    @RequestMapping(value = "/count/parts/{idCuenta}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ParcialidadEntity> listParts(
            @PathVariable("idCuenta") int idCuenta) {
        return parcialidad.listarParcialidades(idCuenta);
    }

    @RequestMapping(value = "/count/parts/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCountPart(
            @RequestBody ParcialidadDto Dto) {
        return parcialidad.createParcialidad(Dto);
    }
    
    @RequestMapping(value = "/updateState/{idCuenta}/{state}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public CuentaEntity updateState(
            @PathVariable("idCuenta") int idCuenta,
            @PathVariable("state") int state) {
        return cuenta.actualizarEstado(state, idCuenta);
    }
    
//    @PutMapping(path = "/State/{noExpedienteTributa}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ApiOperation(value = "Actualiza el estado del Expediente.")
//    public void PutColaborator(@PathVariable(required = true) String noExpedienteTributa) {
//        expedientesService.updateState(1, noExpedienteTributa);
//    }

}
