/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.controller;

import com.cafetito.dtos.agricultor.ParcialidadAgriDto;
import com.cafetito.dtos.agricultor.PesajeAgriDto;
import com.cafetito.dtos.agricultor.TransporteAgriDto;
import com.cafetito.dtos.agricultor.TransportistaAgriDto;
import com.cafetito.entity.peso.AgricultorAgriEntity;
import com.cafetito.entity.peso.ParcialidadAgriEntity;
import com.cafetito.entity.peso.PesajeAgriEntity;
import com.cafetito.entity.peso.TransporteAgriEntity;
import com.cafetito.entity.peso.TransportistaAgriEntity;
import com.cafetito.service.agricultor.IAgricultorAgri;
import com.cafetito.service.agricultor.IParcialidadAgri;
import com.cafetito.service.agricultor.IPesajeAgri;
import com.cafetito.service.agricultor.ITransporteAgri;
import com.cafetito.service.agricultor.ITransportistaAgri;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/Agricultor")
@CrossOrigin(origins = ("*"))
public class AgricultorController {

    @Autowired
    private ITransporteAgri transporte;

    @Autowired
    private ITransportistaAgri transportista;

    @Autowired
    private IPesajeAgri pesaje;

    @Autowired
    private IAgricultorAgri agricultor;

    @Autowired
    private IParcialidadAgri parcialidad;

    /**
     * @param dto
     * @return
     * @url rutas para la entidad transporte
     */
    @RequestMapping(value = "/create/transport", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation( summary = "Crear un nuevo transporte")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public ResponseEntity<TransporteAgriEntity> createTransport(
            @RequestBody TransporteAgriDto dto) {
        return transporte.agregarTransporte(dto);
    }

    @RequestMapping(value = "/list/transport", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "Lista todos los transportes")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public List<TransporteAgriEntity> listTransport() {
        return transporte.listarTransporte();
    }

    /**
     * @param dto
     * @return
     * @url rutas para la entidad transportista
     */
    @RequestMapping(value = "/create/carrier", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation( summary = "Crear un transportista")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public ResponseEntity<TransportistaAgriEntity> createCarrier(
            @RequestBody TransportistaAgriDto dto) {
        return transportista.crearTransportista(dto);
    }

    @RequestMapping(value = "/list/carrier", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "Lista todos los transportistas")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public List<TransportistaAgriEntity> listCarrier() {
        return transportista.listarTransportistas();
    }

    /**
     * @param dto
     * @return
     * @url rutas para la entidad pesaje
     */
    @RequestMapping(value = "/create/weighing", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation( summary = "Crear un nuevo pesaje")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public Boolean crearPesaje(
            @RequestBody PesajeAgriDto dto) {
        return pesaje.crearPesaje(dto);
    }

    @RequestMapping(value = "/list/weighing", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "Lista los pesajes")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public List<PesajeAgriEntity> listarPesajes() {
        return pesaje.listarPesaje();
    }

    /**
     * @param dto
     * @return
     * @url rutas para la entidad agricultor
     */
    @RequestMapping(value = "/show/farmer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "Muestra los datos del agricultor")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public List<AgricultorAgriEntity> mostrarAgricultor() {
        return agricultor.listarAgricultor();
    }

    /**
     * @param idCuenta
     * @return
     * @url rutas para la entidad parcialidad
     */
    @RequestMapping(value = "/count/parts/{idPesaje}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "Lista las parcialidades de un pesaje")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public List<ParcialidadAgriEntity> listParts(
            @PathVariable("idPesaje") int idPesaje) {
        return parcialidad.listarParcialidades(idPesaje);
    }

    @RequestMapping(value = "/count/parts/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation( summary = "Crear una nueva parcialidad")
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Ocurrior un error al insertar los datos")})
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public ResponseEntity<ParcialidadAgriEntity> createCountPart(
            @RequestBody ParcialidadAgriDto Dto) {
        return parcialidad.crearParcialidad(Dto);
    }
    
    @RequestMapping(value = "/assignment/transport/{idPesaje}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "Lista de transportes disponible o que pertenezcan a un pesaje")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public List<TransporteAgriEntity> transportAssignment(
            @PathVariable("idPesaje") Integer idPesaje) {
        return transporte.transportForAssignment(idPesaje);
    }
    
    @RequestMapping(value = "/assignment/carrier/{idPesaje}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation( summary = "Lista de transportistas disponible o que pertenezcan a un pesaje")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public List<TransportistaAgriEntity> carrierAssignment(
            @PathVariable("idPesaje") Integer idPesaje) {
        return transportista.carrierForAssignment(idPesaje);
    }
}
