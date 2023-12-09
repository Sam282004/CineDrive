package com.example.maincinedrive.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maincinedrive.model.entity.Asiento;

import com.example.maincinedrive.model.entity.Sedes;

public interface AsientoRepository extends JpaRepository<Asiento, Integer> {

    List<Asiento> findBySede(Sedes sede);

    List<Asiento> findBySede_Id(Integer idSede);

}
