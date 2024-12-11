package com.scortinas.tarjetasrest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    private String cvvEncriptado;

    @Transient
    @Digits(integer = 3, fraction = 0, message = "El CVV debe tener como máximo 3 números")
    private Integer cvv;

    public Tarjeta() {}

    public Tarjeta(MarcaTarjeta marca, String numero, int cvv) {
        this.marca = marca;
        this.numero = numero;
        this.setCvv(cvv);
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

    public String getCvvEncriptado() {
        return cvvEncriptado;
    }

    public void setCvvEncriptado(String cvvEncriptado) {
        this.cvvEncriptado = cvvEncriptado;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        if (cvv != null) {
            this.cvvEncriptado = encriptarCvv(cvv);
        }
        this.cvv = cvv;
    }

    private String encriptarCvv(int cvv) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(String.valueOf(cvv));
    }

    public boolean verificarCvv(int cvv) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(String.valueOf(cvv), this.cvvEncriptado);
    }
}
