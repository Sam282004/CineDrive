package com.example.maincinedrive.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maincinedrive.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
