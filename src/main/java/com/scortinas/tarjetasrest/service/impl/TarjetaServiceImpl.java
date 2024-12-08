package com.scortinas.tarjetasrest.service.impl;

import com.scortinas.tarjetasrest.domain.Tarjeta;
import com.scortinas.tarjetasrest.service.TarjetaService;
import com.scortinas.tarjetasrest.repository.TarjetaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository repository;

    public TarjetaServiceImpl(TarjetaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tarjeta saveTarjeta(Tarjeta tarjeta) {
        if (repository.existsByNumero(tarjeta.getNumero())) {
            throw new ValidationException("No se puede guardar. El número de tarjeta ya existe.");
        }
        return repository.save(tarjeta);
    }

    @Override
    public List<Tarjeta> getAllTarjetas() {
        return repository.findAll();
    }

    @Override
    public Optional<Tarjeta> getTarjetaById(Long id) {
        return repository.findById(id);
    }

    @Override
    public double calcularTasa(Tarjeta tarjeta, LocalDate fecha) {
        return tarjeta.getMarca().calcularTasa(fecha);
    }
}
