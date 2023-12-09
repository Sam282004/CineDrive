package com.example.maincinedrive.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Asiento> asientos;

    @ManyToOne
    @JoinColumn(name = "id_horario_pelicula")
    private HorarioPelicula horarioPelicula;

    @Column(name = "costo")
    private BigDecimal costo;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    public Compra() {
    }

    public Compra(Integer idCompra, Usuario usuario, HorarioPelicula HorarioPelicula, List<Asiento> asientos,
            BigDecimal costo,
            LocalDateTime fechaCompra) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.horarioPelicula = HorarioPelicula;
        this.asientos = asientos;
        this.costo = costo;
        this.fechaCompra = fechaCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public HorarioPelicula getHorarioPelicula() {
        return horarioPelicula;
    }

    public void setHorarioPelicula(HorarioPelicula horarioPelicula) {
        this.horarioPelicula = horarioPelicula;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        if (costo == null || costo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El costo debe ser mayor que cero");
        }
        this.costo = costo;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        if (fechaCompra == null || fechaCompra.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de compra no puede ser nula ni en el futuro");
        }
        this.fechaCompra = fechaCompra;
    }
}
