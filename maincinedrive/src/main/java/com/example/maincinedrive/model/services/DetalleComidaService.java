package com.example.maincinedrive.model.services;

import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.DetalleComida;
import com.example.maincinedrive.model.repository.DetalleComidaRepository;

@Service
public class DetalleComidaService {

    private final DetalleComidaRepository detalleComidaRepository;

    public DetalleComidaService(DetalleComidaRepository detalleComidaRepository) {
        this.detalleComidaRepository = detalleComidaRepository;
    }

    public DetalleComida saveDetalleComida(DetalleComida detalleComida) {
        return detalleComidaRepository.save(detalleComida);
    }
}
