package com.scortinas.tarjetasrest;

import com.scortinas.tarjetasrest.domain.Operacion;
import com.scortinas.tarjetasrest.domain.Tarjeta;
//import com.scortinas.tarjetasrest.service.OperacionService;
import com.scortinas.tarjetasrest.service.impl.OperacionServiceImpl;
import com.scortinas.tarjetasrest.repository.OperacionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
//import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OperacionServiceImplTest {

    @Mock
    private OperacionRepository repository;

    @InjectMocks
    private OperacionServiceImpl service;

    public OperacionServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOperacionConMontoValido() {
        Tarjeta tarjeta = new Tarjeta();
        Operacion operacion = new Operacion(LocalDate.now(), 100.0, tarjeta);

        when(repository.save(any(Operacion.class))).thenReturn(operacion);

        Operacion result = service.saveOperacion(operacion);

        assertNotNull(result);
        assertEquals(100.0, result.getMonto());
        verify(repository, times(1)).save(operacion);
    }

    @Test
    public void testSaveOperacionConMontoInvalidoThrowsException() {
        Tarjeta tarjeta = new Tarjeta();
        Operacion operacion = new Operacion(LocalDate.now(), -10.0, tarjeta);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveOperacion(operacion);
        });

        assertEquals("El monto debe ser mayor a 0.", exception.getMessage());
    }
}

