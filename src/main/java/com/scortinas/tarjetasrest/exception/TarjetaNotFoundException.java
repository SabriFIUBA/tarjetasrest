package com.scortinas.tarjetasrest.exception;

public class TarjetaNotFoundException extends RuntimeException {
    public TarjetaNotFoundException(String message) {
        super(message);
    }
}