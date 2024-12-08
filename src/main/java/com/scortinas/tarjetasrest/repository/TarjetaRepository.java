
package com.scortinas.tarjetasrest.repository;

import com.scortinas.tarjetasrest.domain.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    boolean existsByNumero(String numero);
}
