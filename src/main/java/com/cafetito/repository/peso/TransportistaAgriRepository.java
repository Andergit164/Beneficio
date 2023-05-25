/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.peso;

import com.cafetito.entity.peso.TransportistaAgriEntity;
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
public interface TransportistaAgriRepository extends JpaRepository<TransportistaAgriEntity, String> {

    @Query("SELECT ta FROM TransportistaAgriEntity ta WHERE disponible = true OR id_pesaje = :idPesaje")
    List<TransportistaAgriEntity> carrierForAssign(@Param("idPesaje") Integer idPesaje);
}
