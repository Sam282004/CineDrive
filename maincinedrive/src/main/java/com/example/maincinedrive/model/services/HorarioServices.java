package com.example.maincinedrive.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.Horario;
import com.example.maincinedrive.model.repository.HorarioRepository;

@Service
public class HorarioServices {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> obtenerTodos() {
        return horarioRepository.findAll();
    }
}
