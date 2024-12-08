package com.scortinas.tarjetasrest.service.impl;

import com.scortinas.tarjetasrest.domain.Operacion;
import com.scortinas.tarjetasrest.repository.OperacionRepository;
import com.scortinas.tarjetasrest.service.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperacionServiceImpl implements OperacionService {

    @Autowired
    private OperacionRepository repository;

    @Override
    public Operacion saveOperacion(Operacion operacion) {
        if (operacion.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }
        return repository.save(operacion);
    }

    @Override
    public Optional<Operacion> getOperacionById(Long id) {
        return repository.findById(id);
    }
}

