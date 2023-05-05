/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.agricultor;

import com.cafetito.entity.agricultor.AgricultorAgriEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anderson
 */
@Repository
public interface AgricultorAgriRepository extends JpaRepository<AgricultorAgriEntity, String>{
    
}
