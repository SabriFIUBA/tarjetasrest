package com.scortinas.tarjetasrest.controller;

import com.scortinas.tarjetasrest.domain.Tarjeta;
import com.scortinas.tarjetasrest.service.TarjetaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService service;

    @PostMapping
    public ResponseEntity<Tarjeta> createTarjeta(@Valid @RequestBody Tarjeta tarjeta) {
        try {
            Tarjeta nuevaTarjeta = service.saveTarjeta(tarjeta);
            return new ResponseEntity<>(nuevaTarjeta, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
}

    @GetMapping
    public ResponseEntity<List<Tarjeta>> getAllTarjetas() {
        List<Tarjeta> tarjetas = service.getAllTarjetas();
        if (tarjetas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tarjetas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> getTarjetaById(@PathVariable Long id) {
        return service.getTarjetaById(id)
                .map(tarjeta -> new ResponseEntity<>(tarjeta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/verificar-cvv")
    public ResponseEntity<Boolean> verificarCvv(@PathVariable Long id, @RequestBody int cvv) {
        try {
            boolean esValido = service.verificarCvv(id, cvv);
            return new ResponseEntity<>(esValido, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}