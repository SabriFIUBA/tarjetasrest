package com.scortinas.tarjetasrest.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CifradoService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encriptar(String dato) {
        return passwordEncoder.encode(dato);
    }

    public boolean verificar(String dato, String hash) {
        return passwordEncoder.matches(dato, hash);
    }
}