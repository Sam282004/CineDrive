package com.example.maincinedrive.model.services;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.maincinedrive.model.entity.Horario;
import com.example.maincinedrive.model.entity.HorarioPelicula;
import com.example.maincinedrive.model.entity.Pelicula;
import com.example.maincinedrive.model.entity.Sedes;
import com.example.maincinedrive.model.repository.HorarioPeliculaRepository;
import com.example.maincinedrive.model.repository.HorarioRepository;
import com.example.maincinedrive.model.repository.PeliculaRepository;
import com.example.maincinedrive.model.repository.SedeRepository;

import org.springframework.dao.DataIntegrityViolationException;

import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class PeliculaServices {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private HorarioPeliculaRepository horarioPeliculaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    SedeRepository sedeRepository;

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public List<Horario> obtenerTodosLosHorarios() {
        return horarioRepository.findAll();
    }

    public List<Sedes> obtenerTodasLasSedes() {
        return sedeRepository.findAll();
    }

    public Pelicula obtenerPeliculaPorNombre(String nombrePelicula) {
        return peliculaRepository.findByTitulo(nombrePelicula);
    }

    public List<Pelicula> obtenerPeliculasPorFechaEstreno(LocalDate fechaEstreno) {
        return peliculaRepository.findByFechaEstrenoAfter(fechaEstreno);
    }

    public Page<Pelicula> obtenerPeliculasPorGenero(String tituloGenero, Pageable pageable) {
        return peliculaRepository.findByGenerosTitulo(tituloGenero, pageable);
    }

    public List<Pelicula> obtenerPeliculasAntesDeFechaEstreno(LocalDate fechaEstreno) {
        return peliculaRepository.findByFechaEstrenoBeforeAndFechaEstrenoIsNotNull(fechaEstreno);
    }

    public void guardarPelicula(Pelicula pelicula) {
        peliculaRepository.save(pelicula);

    }

    @Transactional
    public void asignarHorarioPelicula(HorarioPelicula horarioPelicula) {
        Horario horario = horarioPelicula.getHorario();
        Pelicula pelicula = horarioPelicula.getPelicula();
        Sedes sede = horarioPelicula.getSede();

        if (horario == null || pelicula == null || sede == null) {
            // Manejar el caso cuando uno de los objetos (horario, pelicula, sede) es nulo
            throw new EntityNotFoundException("Horario, Película o Sede obtenidos son nulos");
        }

        // Verificar si la asignación ya existe
        if (horarioPeliculaRepository.existsByHorarioAndPeliculaAndSede(horario, pelicula, sede)) {
            System.out.println("La asignación ya existe. No es necesario volver a insertarla.");
            return; // Salir del método si ya existe
        }

        // Si no existe, proceder con la inserción
        try {
            HorarioPelicula savedHorarioPelicula = horarioPeliculaRepository.save(horarioPelicula);
            horarioPelicula.setHorario(savedHorarioPelicula.getHorario());
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error de violación de integridad: " + e.getMessage());
            // Puedes manejar esta excepción según tus necesidades
        }
    }
}
