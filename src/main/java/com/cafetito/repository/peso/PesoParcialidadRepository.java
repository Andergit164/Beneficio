/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.peso;

import com.cafetito.entity.peso.PesoParcialidadEntity;
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
public interface PesoParcialidadRepository extends JpaRepository<PesoParcialidadEntity, Integer> {

    @Query("SELECT p FROM PesoParcialidadEntity p WHERE id_cuenta = :idCuenta AND aceptado = true")
    List<PesoParcialidadEntity> listParcialidad(@Param("idCuenta") Integer idCuenta);
}
