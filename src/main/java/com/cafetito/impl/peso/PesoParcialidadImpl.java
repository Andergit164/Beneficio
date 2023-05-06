/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cafetito.impl.peso;

import com.cafetito.entity.peso.PesoParcialidadEntity;
import com.cafetito.repository.peso.PesoParcialidadRepository;
import com.cafetito.service.peso.IPesoParcialidad;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class PesoParcialidadImpl implements IPesoParcialidad {

    @Autowired
    private PesoParcialidadRepository pesoParcialidadRepository;

    @Override
    public List<PesoParcialidadEntity> listarParcialidades(Integer idCuenta) {
        return pesoParcialidadRepository.listParcialidad(idCuenta);
    }

    @Override
    public PesoParcialidadEntity actualiarPeso(int idParcialidad, double peso) {
        final PesoParcialidadEntity updateWeight = pesoParcialidadRepository.findById(idParcialidad).orElse(null);
        if (updateWeight != null) {
            updateWeight.setPesoObtenido(peso);
            updateWeight.setFechaPeso(new Date());
        } else {
            return null;
        }

        return pesoParcialidadRepository.save(updateWeight);
    }

}
