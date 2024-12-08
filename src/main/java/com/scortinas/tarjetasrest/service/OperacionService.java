package com.scortinas.tarjetasrest.service;

import com.scortinas.tarjetasrest.domain.Operacion;

import java.util.Optional;

public interface OperacionService {
    Operacion saveOperacion(Operacion operacion);
    Optional<Operacion> getOperacionById(Long id);
}

