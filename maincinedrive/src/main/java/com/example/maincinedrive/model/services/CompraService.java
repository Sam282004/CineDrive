package com.example.maincinedrive.model.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.Asiento;
import com.example.maincinedrive.model.entity.Compra;
import com.example.maincinedrive.model.entity.EstadoAsiento;
import com.example.maincinedrive.model.repository.CompraRepository;

import jakarta.transaction.Transactional;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository, AsientoService asientoService) {
        this.compraRepository = compraRepository;
        this.asientoService = asientoService;
    }

    private final AsientoService asientoService;

    // constructor e inicialización

    @Transactional
    public void procesarCompra(List<Asiento> asientosSeleccionados, BigDecimal costoTotal) {
        // Verificar disponibilidad de asientos
        verificarDisponibilidad(asientosSeleccionados);

        // Crear instancia de Compra
        Compra compra = new Compra();
        compra.setAsientos(asientosSeleccionados);
        compra.setCosto(costoTotal);
        compra.setFechaCompra(LocalDateTime.now());

        // Guardar la compra en la base de datos
        compra = compraRepository.save(compra);

        // Actualizar estado de los asientos y asociarlos a la compra
        actualizarEstadoAsientos(asientosSeleccionados, compra);
    }

    private void verificarDisponibilidad(List<Asiento> asientosSeleccionados) {
        for (Asiento asiento : asientosSeleccionados) {
            if (asiento.getEstado() != EstadoAsiento.DISPONIBLE) {
                throw new IllegalStateException("El asiento " + asiento.getNombreAsiento() + " no está disponible.");
            }
        }
    }

    private void actualizarEstadoAsientos(List<Asiento> asientosSeleccionados, Compra compra) {
        for (Asiento asiento : asientosSeleccionados) {
            asiento.setEstado(EstadoAsiento.OCUPADO);
            asientoService.saveAsiento(asiento);
        }
    }

    @Transactional
    public void eliminarCompra(int idCompra) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la compra con ID: " + idCompra));

        // Verificar disponibilidad de asientos (opcional, dependiendo de tus
        // requisitos)

        // Eliminar la compra
        compraRepository.delete(compra);

        // Cambiar el estado de los asientos asociados a DISPONIBLE
        cambiarEstadoAsientos(compra.getAsientos());
    }

    private void cambiarEstadoAsientos(List<Asiento> asientos) {
        for (Asiento asiento : asientos) {
            asiento.setEstado(EstadoAsiento.DISPONIBLE);
            asientoService.saveAsiento(asiento);
        }
    }
}
