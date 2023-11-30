package com.example.maincinedrive.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.maincinedrive.model.entity.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    @Query("SELECT p FROM Pelicula p WHERE p.fechaEstreno > :fechaEstreno")
    List<Pelicula> findByFechaEstrenoAfter(@Param("fechaEstreno") LocalDate fechaEstreno);

    @Query("SELECT p FROM Pelicula p JOIN p.generos g WHERE g.titulo = :titulo")
    Page<Pelicula> findByGenerosTitulo(@Param("titulo") String titulo, Pageable pageable);

    List<Pelicula> findByFechaEstrenoBeforeAndFechaEstrenoIsNotNull(LocalDate fechaEstreno);

}
