package com.example.maincinedrive.model.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.Asiento;

import com.example.maincinedrive.model.entity.Sedes;
import com.example.maincinedrive.model.repository.AsientoRepository;

@Service
public class AsientoService {
    private final AsientoRepository asientoRepository;

    public AsientoService(AsientoRepository asientoRepository) {
        this.asientoRepository = asientoRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(AsientoService.class);

    public List<Asiento> obtenerListaDeAsientos() {
        return asientoRepository.findAll();
    }

    public Asiento saveAsiento(Asiento asiento) {
        return asientoRepository.save(asiento);
    }

    public List<Asiento> obtenerAsientoPorSede(Sedes sede) {
        return asientoRepository.findBySede(sede);
    }

    public void guardarAsientos(List<Asiento> asientos) {
        asientoRepository.saveAll(asientos);
    }

    public List<Asiento> obtenerAsientosPorSede(Integer idSede) {
        List<Asiento> asientos = asientoRepository.findBySede_Id(idSede);
        logger.debug("Asientos encontrados para la sede {}: {}", idSede, asientos);
        return asientos;
    }

}
