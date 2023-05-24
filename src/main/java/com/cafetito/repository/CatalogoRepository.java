/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository;

import com.cafetito.entity.CatalogoEntity;
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
public interface CatalogoRepository extends JpaRepository<CatalogoEntity, Integer>{
    
    @Query("SELECT ce FROM CatalogoEntity ce WHERE codigo_catalogo = :catalogo")
    List<CatalogoEntity> listarCatalogo(@Param("catalogo") Integer catalogo);
    
}
