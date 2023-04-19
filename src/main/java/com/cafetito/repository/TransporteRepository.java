/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository;

import com.cafetito.entity.TransporteEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Anderson
 */
public interface TransporteRepository extends JpaRepository<TransporteEntity, String> {

    @Query("SELECT t FROM TransporteEntity t WHERE nit_agricultor = :nitAgricultor")
    List<TransporteEntity> listTransport(@Param("nitAgricultor") String nitAgricultor);

}
