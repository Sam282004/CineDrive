package com.example.maincinedrive.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maincinedrive.model.entity.Sedes;

public interface SedeRepository extends JpaRepository<Sedes, Integer> {
    Sedes findByNombre(String nombre);

}
