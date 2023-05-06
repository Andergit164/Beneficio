/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.agricultor;

import com.cafetito.entity.agricultor.TransporteAgriEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anderson
 */
@Repository
@Transactional(transactionManager = "agricultorTransactionManager")
public interface TransporteAgriRepository extends JpaRepository<TransporteAgriEntity, String>{
    
}
