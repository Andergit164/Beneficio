/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.agricultor;

import com.cafetito.entity.peso.AgricultorAgriEntity;
import com.cafetito.repository.peso.AgricultorAgriRepository;
import com.cafetito.service.agricultor.IAgricultorAgri;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class AgricultorAgriImpl implements IAgricultorAgri{
    
    @Autowired
    private AgricultorAgriRepository agricultor;

    @Override
    public List<AgricultorAgriEntity> listarAgricultor() {
        return agricultor.findAll();
    }
    
}
