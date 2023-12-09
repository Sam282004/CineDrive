package com.example.maincinedrive.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "sedes")
public class Sedes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede")
    private Integer id;

    private String nombre;

    private String direccion;

    private String horarios_operacion;

    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    private byte[] imagen;

    public Sedes() {
    }

    public Sedes(Integer id, String nombre, String direccion, String horarios_operacion, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarios_operacion = horarios_operacion;
        this.imagen = imagen;
    }

    public Sedes(String nombre, String direccion, String horarios_operacion, byte[] imagen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarios_operacion = horarios_operacion;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorarios_operacion() {
        return horarios_operacion;
    }

    public void setHorarios_operacion(String horarios_operacion) {
        this.horarios_operacion = horarios_operacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
