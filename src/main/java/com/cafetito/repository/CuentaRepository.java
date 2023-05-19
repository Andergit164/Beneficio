/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository;

import com.cafetito.entity.CuentaEntity;
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
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {

    @Query("SELECT cu FROM CuentaEntity cu WHERE nit_agricultor = :nitAgricultor")
    List<CuentaEntity> listarCuentas(@Param("nitAgricultor") String nitAgricultor);
    
    @Query("SELECT cu FROM CuentaEntity cu WHERE nit_agricultor = :nitAgricultor AND id_estado = :state")
    List<CuentaEntity> listarCuentasEstado(@Param("nitAgricultor") String nitAgricultor, @Param("state") Integer state);

    @Query(value = "SELECT count(cu)+1 FROM CuentaEntity cu")
    int requestNexVal();
}
