/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;


import com.cafetito.entity.AgricultorEntity;
import com.cafetito.repository.AgricultorRepository;
import com.cafetito.service.IAgricultor;import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.stereotype.Service;import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class AgricultorImpl implements IAgricultor {
    
    @Autowired
    private AgricultorRepository agricultorRepository;

    @Override
    public List<AgricultorEntity> listaAgricultores() {
       return agricultorRepository.findAll();
    }

}
