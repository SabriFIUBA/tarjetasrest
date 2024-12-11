package com.scortinas.tarjetasrest;

import com.scortinas.tarjetasrest.domain.MarcaTarjeta;
import com.scortinas.tarjetasrest.domain.Tarjeta;
import com.scortinas.tarjetasrest.repository.TarjetaRepository;
import com.scortinas.tarjetasrest.service.TarjetaService;
import com.scortinas.tarjetasrest.service.impl.TarjetaServiceImpl;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TarjetaServiceImplTest {

    private TarjetaRepository repository;
    private TarjetaService service;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(TarjetaRepository.class);
        passwordEncoder = new BCryptPasswordEncoder();
        service = new TarjetaServiceImpl(repository);
    }

    @Test
    void guardarTarjetaConNumeroDuplicadoThrowsExceptionElsePass() {
        Mockito.when(repository.existsByNumero("123456789012")).thenReturn(true);
        Tarjeta tarjeta = new Tarjeta(MarcaTarjeta.VISA, "123456789012", 123);

        assertThrows(ValidationException.class, () -> service.saveTarjeta(tarjeta));
        verify(repository, never()).save(any(Tarjeta.class));
    }

    @Test
    void guardarTarjetaCifraCvvCorrectamente() {
        Mockito.when(repository.existsByNumero("123456789012")).thenReturn(false);
        Tarjeta tarjeta = new Tarjeta(MarcaTarjeta.VISA, "123456789012", 123);

        service.saveTarjeta(tarjeta);

        verify(repository, times(1)).save(argThat(savedTarjeta ->
            passwordEncoder.matches("123", savedTarjeta.getCvvEncriptado()) && savedTarjeta.getCvv() == null
        ));
    }

    @Test
    void verificarCvvValidoDevuelveTrue() {
        Tarjeta tarjeta = new Tarjeta(MarcaTarjeta.VISA, "123456789012", 123);
        tarjeta.setCvvEncriptado(passwordEncoder.encode("123"));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(tarjeta));

        boolean resultado = service.verificarCvv(1L, 123);

        assertTrue(resultado);
    }

    @Test
    void verificarCvvInvalidoDevuelveFalse() {
        Tarjeta tarjeta = new Tarjeta(MarcaTarjeta.VISA, "123456789012", 123);
        tarjeta.setCvvEncriptado(passwordEncoder.encode("123"));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(tarjeta));

        boolean resultado = service.verificarCvv(1L, 456);

        assertFalse(resultado);
    }

    @Test
    void verificarCvvTarjetaNoExistenteLanzaExcepcion() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.verificarCvv(1L, 123));
    }
}
