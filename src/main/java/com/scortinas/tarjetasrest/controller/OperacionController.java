package com.scortinas.tarjetasrest.controller;

import com.scortinas.tarjetasrest.domain.Operacion;
import com.scortinas.tarjetasrest.domain.Tarjeta;
import com.scortinas.tarjetasrest.dto.OperacionRequest;
import com.scortinas.tarjetasrest.exception.TarjetaNotFoundException;
import com.scortinas.tarjetasrest.service.OperacionService;
import com.scortinas.tarjetasrest.service.TarjetaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/operaciones")
public class OperacionController {

    @Autowired
    private OperacionService operacionService;

    @Autowired
    private TarjetaService tarjetaService;

   @PostMapping
    public ResponseEntity<Operacion> guardarOperacion(@RequestBody @Valid OperacionRequest operacionRequest) {
        Tarjeta tarjeta = tarjetaService.getTarjetaById(operacionRequest.getTarjetaId())
                .orElseThrow(() -> new TarjetaNotFoundException("Tarjeta no encontrada"));

        Operacion operacion = new Operacion(LocalDate.now(), operacionRequest.getMonto(), tarjeta);
        return new ResponseEntity<>(operacionService.saveOperacion(operacion), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/tasa")
    public ResponseEntity<Double> obtenerTasaPorOperacionById(@PathVariable Long id) {
        Operacion operacion = operacionService.getOperacionById(id)
                .orElseThrow(() -> new RuntimeException("Operación no encontrada"));

        double tasa = operacion.getTarjeta().getMarca().calcularTasa(operacion.getFecha());
        return ResponseEntity.ok(tasa);
    }
}

