package com.example.maincinedrive.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.HorarioPelicula;

import com.example.maincinedrive.model.repository.HorarioPeliculaRepository;
import com.example.maincinedrive.model.repository.HorarioRepository;

@Service
public class HorarioPeliculaService {

    @Autowired
    HorarioRepository horarioRepository;

    private final HorarioPeliculaRepository horarioPeliculaRepository;

    public HorarioPeliculaService(HorarioPeliculaRepository horarioPeliculaRepository) {
        this.horarioPeliculaRepository = horarioPeliculaRepository;
    }

    public List<HorarioPelicula> obtenerTodosLosHorariosPelicula() {
        return horarioPeliculaRepository.findAll();
    }

}
