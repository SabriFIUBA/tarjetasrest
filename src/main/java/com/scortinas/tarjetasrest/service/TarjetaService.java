package com.scortinas.tarjetasrest.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.scortinas.tarjetasrest.domain.Tarjeta;

public interface TarjetaService {
    Tarjeta saveTarjeta(Tarjeta tarjeta);

    List<Tarjeta> getAllTarjetas();

    public double calcularTasa(Tarjeta tarjeta, LocalDate fecha);

    Optional<Tarjeta> getTarjetaById(Long id);
}