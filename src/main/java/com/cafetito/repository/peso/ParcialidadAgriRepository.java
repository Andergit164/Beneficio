/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.peso;

import com.cafetito.entity.peso.ParcialidadAgriEntity;
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
public interface ParcialidadAgriRepository extends JpaRepository<ParcialidadAgriEntity, Integer>{
    
//    List<ParcialidadAgriEntity> findByIdPesaje(Integer idPesaje);
    
     @Query("SELECT p FROM ParcialidadAgriEntity p WHERE id_pesaje = :idPesaje")
    List<ParcialidadAgriEntity> listarParcialidades(@Param("idPesaje") Integer idPesaje);
    
     @Query(value = "SELECT count(cu)+1 FROM ParcialidadAgriEntity cu")
    int requestNexValAgricultor();
}
