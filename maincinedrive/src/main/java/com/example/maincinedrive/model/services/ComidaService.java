package com.example.maincinedrive.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.Comida;
import com.example.maincinedrive.model.repository.ComidaRepository;

@Service
public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;

    public List<Comida> obtenerListaDeComidas() {
        return comidaRepository.findAll();
    }
}
