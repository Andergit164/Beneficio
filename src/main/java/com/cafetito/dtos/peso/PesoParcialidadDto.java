package com.cafetito.dtos.peso;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Anderson
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PesoParcialidadDto implements Serializable{
    
    private Integer idParcialidad;
    private Integer idCuenta;
    private String observaciones;
    private Double pesoObtenido;
    private Integer codigoPeso;
    private String usuarioModifico;
}
