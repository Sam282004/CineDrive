package com.example.maincinedrive.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleComida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_comida")
    private Integer idDetalleComida;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_comida")
    private Comida comida;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "nombre_bocadito")
    private String nombreBocadito;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "precio_total")
    private BigDecimal precioTotal;

    public DetalleComida() {
    }

    public DetalleComida(Integer idDetalleComida, Compra compra, Comida comida, Integer cantidad, String nombreBocadito,
            BigDecimal precioUnitario, BigDecimal precioTotal) {
        this.idDetalleComida = idDetalleComida;
        this.compra = compra;
        this.comida = comida;
        this.cantidad = cantidad;
        this.nombreBocadito = nombreBocadito;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }

    public Integer getIdDetalleComida() {
        return idDetalleComida;
    }

    public void setIdDetalleComida(Integer idDetalleComida) {
        this.idDetalleComida = idDetalleComida;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreBocadito() {
        return nombreBocadito;
    }

    public void setNombreBocadito(String nombreBocadito) {
        this.nombreBocadito = nombreBocadito;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

}
