/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.repository.peso;

import com.cafetito.entity.peso.PesoCuentaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anderson
 */
@Repository
public interface PesoCuentaBeneficioRepository extends JpaRepository<PesoCuentaEntity, Integer>{
    
    @Query("SELECT p FROM PesoCuentaEntity p WHERE id_estado = 2 or id_estado = 3 or id_estado = 4")
    List<PesoCuentaEntity> listCuentas();
}
