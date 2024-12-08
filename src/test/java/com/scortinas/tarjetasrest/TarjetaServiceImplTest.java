package com.scortinas.tarjetasrest;

import com.scortinas.tarjetasrest.domain.MarcaTarjeta;
import com.scortinas.tarjetasrest.domain.Tarjeta;
import com.scortinas.tarjetasrest.repository.TarjetaRepository;
import com.scortinas.tarjetasrest.service.TarjetaService;
import com.scortinas.tarjetasrest.service.impl.TarjetaServiceImpl;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TarjetaServiceImplTest {

    private final TarjetaRepository repository = Mockito.mock(TarjetaRepository.class);
    private final TarjetaService service = new TarjetaServiceImpl(repository);

    @Test
    void guardarTarjetaConNumeroDuplicadoThrowsExceptionElsePass() {

        Mockito.when(repository.existsByNumero("123456789012")).thenReturn(true);
        Tarjeta tarjeta = new Tarjeta(MarcaTarjeta.VISA, "123456789012", 123);
        assertThrows(ValidationException.class, () -> service.saveTarjeta(tarjeta));
    }
}
