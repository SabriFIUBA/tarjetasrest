package com.scortinas.tarjetasrest.service.impl;

import com.scortinas.tarjetasrest.domain.Tarjeta;
import com.scortinas.tarjetasrest.repository.TarjetaRepository;
import com.scortinas.tarjetasrest.service.TarjetaService;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    private final TarjetaRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TarjetaServiceImpl(TarjetaRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Tarjeta saveTarjeta(Tarjeta tarjeta) {
        if (repository.existsByNumero(tarjeta.getNumero())) {
            throw new ValidationException("No se puede guardar. El número de tarjeta ya existe.");
        }

        tarjeta.setCvvEncriptado(passwordEncoder.encode(String.valueOf(tarjeta.getCvv())));
        tarjeta.setCvv(null); // Limpiar el CVV en texto plano

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

    @Override
    public boolean verificarCvv(Long id, int cvv) {
        Tarjeta tarjeta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        return passwordEncoder.matches(String.valueOf(cvv), tarjeta.getCvvEncriptado());
    }
}
