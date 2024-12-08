package com.scortinas.tarjetasrest.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private double monto;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

    public Operacion() {}

    public Operacion(LocalDate fecha, double monto, Tarjeta tarjeta) {
        this.fecha = fecha;
        this.monto = monto;
        this.tarjeta = tarjeta;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}
