package com.example.maincinedrive.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categoria {

    @Id
    @Column(name = "id_categoria")
    private Integer id;

    private String categoria;

    public Categoria() {
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria(Integer id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
