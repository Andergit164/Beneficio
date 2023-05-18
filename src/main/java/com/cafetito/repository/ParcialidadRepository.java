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
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anderson
 */
@Repository
public interface ParcialidadRepository extends JpaRepository<ParcialidadEntity, Integer>{
    
    @Query("SELECT p FROM ParcialidadEntity p "
            + "WHERE id_cuenta = :idCuenta "
            + "AND fecha_peso is not null")
    List<ParcialidadEntity> listParcialidadPeso(@Param("idCuenta") Integer idCuenta);
    
    @Query("SELECT p FROM ParcialidadEntity p WHERE id_cuenta = :idCuenta")
    List<ParcialidadEntity> listParcialidad(@Param("idCuenta") Integer idCuenta);
    
    @Query(value = "SELECT count(cu)+1 FROM ParcialidadEntity cu")
    int requestNexVal();
    
    @Query(value = "SELECT count(cu) FROM ParcialidadEntity cu "
            + "WHERE id_cuenta = :idCuenta "
            + "AND fecha_peso is not null")
    int requestCountParts(@Param("idCuenta") Integer idCuenta);
    
}
