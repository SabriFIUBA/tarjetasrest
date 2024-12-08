package com.scortinas.tarjetasrest.repository;

import com.scortinas.tarjetasrest.domain.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {}

