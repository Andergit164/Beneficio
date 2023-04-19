/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository;

import com.cafetito.entity.TransportistaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Anderson
 */
public interface TransportistaRepository extends JpaRepository<TransportistaEntity, Integer>{
    
     @Query("SELECT t3 FROM TransportistaEntity t3 WHERE nit_agricultor = :nitAgricultor")
    List<TransportistaEntity> listCarriersByAgricultor(@Param("nitAgricultor") String nitAgricultor);
    
}
