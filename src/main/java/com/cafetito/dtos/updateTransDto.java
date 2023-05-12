/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.dtos;

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
public class updateTransDto {
    
    private String observaciones;
    private Boolean activo;
    private String usuarioModifico;
}
