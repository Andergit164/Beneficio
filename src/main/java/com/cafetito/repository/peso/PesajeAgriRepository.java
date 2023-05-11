/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.peso;

import com.cafetito.entity.peso.PesajeAgriEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anderson
 */
@Repository
public interface PesajeAgriRepository extends JpaRepository<PesajeAgriEntity, Integer> {
    
    @Query(value = "SELECT count(cu)+1 FROM PesajeAgriEntity cu")
    int requestNexValPesaje();
}
