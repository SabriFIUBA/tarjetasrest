package com.scortinas.tarjetasrest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OperacionRequest {

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto debe ser mayor a 0")
    private Double monto;

    @NotNull(message = "El ID de la tarjeta es obligatorio")
    private Long tarjetaId;

    // Constructor por defecto
    public OperacionRequest() {
    }

    // Constructor con parámetros
    public OperacionRequest(Double monto, Long tarjetaId) {
        this.monto = monto;
        this.tarjetaId = tarjetaId;
    }

    // Getters y Setters
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Long getTarjetaId() {
        return tarjetaId;
    }

    public void setTarjetaId(Long tarjetaId) {
        this.tarjetaId = tarjetaId;
    }
}
