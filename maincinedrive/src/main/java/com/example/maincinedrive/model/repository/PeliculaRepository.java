package com.example.maincinedrive.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maincinedrive.model.entity.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

}
