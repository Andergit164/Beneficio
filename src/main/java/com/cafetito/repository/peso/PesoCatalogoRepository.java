/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.peso;

import com.cafetito.entity.peso.PesoCatalogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anderson
 */
@Repository
public interface PesoCatalogoRepository extends JpaRepository<PesoCatalogoEntity, Integer>{
    
}
