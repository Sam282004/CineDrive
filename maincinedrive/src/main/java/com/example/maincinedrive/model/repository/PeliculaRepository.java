package com.example.maincinedrive.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.maincinedrive.model.entity.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    @Query("SELECT p FROM Pelicula p WHERE p.fechaEstreno > :fechaEstreno")
    List<Pelicula> findByFechaEstrenoAfter(@Param("fechaEstreno") LocalDate fechaEstreno);

    List<Pelicula> findByFechaEstrenoBeforeAndFechaEstrenoIsNotNull(LocalDate fechaEstreno);

}
