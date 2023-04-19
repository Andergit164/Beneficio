/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository;

import com.cafetito.entity.ParcialidadEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Anderson
 */
public interface ParcialidadRepository extends JpaRepository<ParcialidadEntity, Integer>{
    
    @Query("SELECT p FROM ParcialidadEntity p WHERE id_cuenta = :idCuenta")
    List<ParcialidadEntity> listParcialidad(@Param("idCuenta") Integer idCuenta);
    
}
