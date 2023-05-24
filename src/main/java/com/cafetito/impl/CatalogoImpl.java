/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl;

import com.cafetito.entity.CatalogoEntity;
import com.cafetito.repository.CatalogoRepository;
import com.cafetito.service.ICatalogo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class CatalogoImpl implements ICatalogo{
    
    @Autowired
    private CatalogoRepository catalogoRepository;

    @Override
    public List<CatalogoEntity> listarCatalogo(Integer catalogo) {
        return catalogoRepository.listarCatalogo(catalogo);
    }
    
}
