package com.example.maincinedrive.model.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HorarioPelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario_pelicula")
    private Long idHorarioPelicula;

    @ManyToOne()
    @JoinColumn(name = "id_horario")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "id_sede")
    private Sedes sede;

    private Integer duracion;
    private LocalTime horaInicio;
    private Integer asientosDisponibles;

    private BigDecimal precioEntrada;

    private String formatoProyeccion;

    private String estadoProyeccion;

    public HorarioPelicula() {
    }

    public HorarioPelicula(Long idHorarioPelicula, Horario horario, Pelicula pelicula, Sedes sede, Integer duracion,
            LocalTime horaInicio, Integer asientosDisponibles, BigDecimal precioEntrada, String formatoProyeccion,
            String estadoProyeccion) {
        this.idHorarioPelicula = idHorarioPelicula;
        this.horario = horario;
        this.pelicula = pelicula;
        this.sede = sede;
        this.duracion = duracion;
        this.horaInicio = horaInicio;
        this.asientosDisponibles = asientosDisponibles;
        this.precioEntrada = precioEntrada;
        this.formatoProyeccion = formatoProyeccion;
        this.estadoProyeccion = estadoProyeccion;
    }

    public Long getIdHorarioPelicula() {
        return idHorarioPelicula;
    }

    public void setIdHorarioPelicula(Long idHorarioPelicula) {
        this.idHorarioPelicula = idHorarioPelicula;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sedes getSede() {
        return sede;
    }

    public void setSede(Sedes sede) {
        this.sede = sede;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(Integer asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public BigDecimal getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(BigDecimal precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public String getFormatoProyeccion() {
        return formatoProyeccion;
    }

    public void setFormatoProyeccion(String formatoProyeccion) {
        this.formatoProyeccion = formatoProyeccion;
    }

    public String getEstadoProyeccion() {
        return estadoProyeccion;
    }

    public void setEstadoProyeccion(String estadoProyeccion) {
        this.estadoProyeccion = estadoProyeccion;
    }

}