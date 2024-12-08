package com.scortinas.tarjetasrest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private MarcaTarjeta marca;

    @Column(length = 12, nullable = false, unique = true)
    @Size(max = 12, message = "El número no puede superar los 12 caracteres.")
    private String numero;

    @Column(nullable = false)
    @Digits(integer = 3, fraction = 0, message = "El CVV debe tener como máximo 3 números")
    private int cvv;

    //constructores
    public Tarjeta() {}

    public Tarjeta(MarcaTarjeta marca, String numero, int cvv) {
        this.marca = marca;
        this.numero = numero;
        this.cvv = cvv;
    }

    //getters, setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MarcaTarjeta getMarca() {
        return marca;
    }

    public void setMarca(MarcaTarjeta marca) {
        this.marca = marca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}