package com.scortinas.tarjetasrest.controller;

import com.scortinas.tarjetasrest.domain.Tarjeta;
import com.scortinas.tarjetasrest.service.TarjetaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService service;

    @PostMapping
    public ResponseEntity<Tarjeta> createTarjeta(@Valid @RequestBody Tarjeta tarjeta) {
        return new ResponseEntity<>(service.saveTarjeta(tarjeta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tarjeta>> getAllTarjetas() {
        return new ResponseEntity<>(service.getAllTarjetas(), HttpStatus.OK);
    }
}
