package com.example.maincinedrive.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.maincinedrive.model.entity.Horario;
import com.example.maincinedrive.model.entity.HorarioPelicula;
import com.example.maincinedrive.model.entity.Pelicula;
import com.example.maincinedrive.model.entity.Sedes;

public interface HorarioPeliculaRepository extends JpaRepository<HorarioPelicula, Long> {
    @Query("SELECT CASE WHEN COUNT(hp) > 0 THEN true ELSE false END FROM HorarioPelicula hp WHERE hp.horario = :horario AND hp.pelicula = :pelicula AND hp.sede = :sede")
    boolean existsByHorarioAndPeliculaAndSede(Horario horario, Pelicula pelicula, Sedes sede);

    Optional<HorarioPelicula> findByPeliculaIdAndSedeId(Integer idPelicula, Integer idSede);

}
